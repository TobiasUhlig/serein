/**
 * Copyright (C) 2015 Tobias Uhlig (tobias.uhlig@unibw.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.terministic.serein.core.termination;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.TerminationCondition;

public class TerminationConditionTime<P> implements TerminationCondition<P> {


	private final long timeLimit;
	private long endTime = 0;
	
	public TerminationConditionTime(long milliseconds){
		timeLimit = milliseconds;
		reset();
	}
	
	@Override
	public boolean doContinue() {
		boolean result = true;
		if (endTime == 0) {
			endTime = System.currentTimeMillis() + timeLimit;;
		}
		
		if (System.currentTimeMillis() > endTime) {
			result = false;
			reset();
		}
		return result;
	}

	public void reset() {
		endTime = 0;	
	}

	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		// I do not need the reference		
		reset();
	}

}
