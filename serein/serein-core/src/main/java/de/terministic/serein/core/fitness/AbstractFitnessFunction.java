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
package de.terministic.serein.core.fitness;

import de.terministic.serein.api.FitnessFunction;
import de.terministic.serein.api.Individual;

public abstract class AbstractFitnessFunction<P> extends AbstractFitnessComparator<P, Double> implements FitnessFunction<P> {
	
	
	@Override
	public int compare(Individual<P, ?> individual1, Individual<P, ?> individual2) {
		int result = super.compare(individual1, individual2);
		if (!isNaturalOrder()) {
			result = -result;
		}
		return result;
	}

	
	@Override
	public String fitnessToString(Individual<P, ?> individual) {
		return THREE_DIGITS.format(getFitness(individual));
	}
	
	@Override
	protected void update(Individual<P, ?> individual1, Individual<P, ?> individual2, int decission){
		if (!isNaturalOrder()) {
			decission = -decission;
		}
		super.update(individual1, individual2, decission);
	}
}
