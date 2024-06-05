#include <voicesmith/io/AudioSource.h>

#include <voicesmith/Source.h>

AudioSource::AudioSource(const std::optional<int> device,
                         const std::optional<float> samplerate,
                         const std::optional<size_t> blocksize,
                         const std::shared_ptr<AudioEffect> effect,
                         const std::shared_ptr<AudioBlockQueue> queue) :
  AudioStream(oboe::Direction::Input, device, samplerate, blocksize),
  effect(effect),
  queue((queue != nullptr) ? queue : std::make_shared<AudioBlockQueue>()) {

  if (effect) {
    onopen([this]() {
      this->effect->reset(this->samplerate(), this->blocksize());
    });
  }

  onstart([this]() {
    this->index = {0, 0};
  });
}

std::shared_ptr<AudioEffect> AudioSource::fx() const {
  return effect;
}

std::shared_ptr<AudioBlockQueue> AudioSource::fifo() const {
  return queue;
}

void AudioSource::callback(const std::span<float> samples) {
  const bool ok = queue->write([&](AudioBlock& block) {
    if (effect) {
      effect->apply(index.inner, samples, block);
    } else {
      block.copyfrom(samples);
    }
    ++index.inner;
  });

  if (!ok) {
    LOG(WARNING) << $("Audio source fifo overflow! #{0}", index.outer);
  }

  ++index.outer;
}
