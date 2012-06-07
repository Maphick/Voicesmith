/*******************************************************************************
 * src/de/jurihock/voicesmith/activities/DenoiseActivity.java
 * is part of the Voicesmith project
 * <http://voicesmith.jurihock.de>
 * 
 * Copyright (C) 2011-2012 Juergen Hock
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package de.jurihock.voicesmith.activities;

import android.os.Bundle;
import de.jurihock.voicesmith.R;
import de.jurihock.voicesmith.threads.DenoiseThread;

public final class DenoiseActivity extends AudioActivity
{
	public DenoiseActivity()
	{
		super(DenoiseThread.class,
			R.string.titDenoise,
			R.string.sumDenoise);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.others);
	}
}
