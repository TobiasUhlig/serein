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
package de.terministic.serein.core.selection.survival;

import de.terministic.serein.api.FitnessFunction;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;
import de.terministic.serein.core.Populations;

public class SafeSimulatedAnnealingSelection<P> extends SimulatedAnnealingSelection<P> {

	/**
	 * This selection approach does not implicitly rely on the order of parent
	 * and offspring population. However it only works if ancestor tracking is
	 * active, since it uses it to find the corresponding parent for an
	 * offspring. 
	 * 
	 * @param fitnessFunction
	 * @param initialTemperature
	 * @param coolDownFactor
	 */
	public SafeSimulatedAnnealingSelection(FitnessFunction<P> fitnessFunction, double initialTemperature, double coolDownFactor) {
		super(fitnessFunction, initialTemperature, coolDownFactor);
	}
	
	@Override
	public Population<P> selectSurvivors(Population<P> parents, Population<P> offspring) {
		Population<P> result = Populations.<P>emptyPopulation();
		for(Individual<P, ?> i:offspring){
			result.add(metropolisHastingsSelection(i, i.<Population<P>>getProperty("Parents").get(0)));		
		}
		updateTemperature();
		return result;
	}

}
