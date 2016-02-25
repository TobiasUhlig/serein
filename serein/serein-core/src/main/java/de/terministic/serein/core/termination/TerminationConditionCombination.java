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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.TerminationCondition;

public class TerminationConditionCombination<P> implements TerminationCondition<P> {
	
	
	private final List<TerminationCondition<P>> conditions;
	
	public TerminationConditionCombination(List<TerminationCondition<P>> conditions) {
		this.conditions = new ArrayList<TerminationCondition<P>>(conditions);
	}
	
	@SafeVarargs
	public TerminationConditionCombination(TerminationCondition<P>... conditions) {
		this(Arrays.asList(conditions));
	}
	
	@Override
	public void setEnvironment(EvolutionEnvironment<P> environment) {
		for (TerminationCondition<P> tc:conditions){
			tc.setEnvironment(environment);
		}
	}

	@Override
	public boolean doContinue() {
		boolean result = false;
		for (TerminationCondition<P> c:conditions) {
			result = result && c.doContinue();
		}
		
		return result;
	}

}
