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

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SurvivorSelection;
import de.terministic.serein.core.PopulationImpl;

public class CoupledSelection<P> implements SurvivorSelection<P> {
	private final FitnessComparator<P> comparator;
	
	public CoupledSelection(FitnessComparator<P> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		// I need no information from reference
	}
	
	@Override
	public Population<P> selectSurvivors(Population<P> parents, Population<P> offspring) {
		Population<P> result = new PopulationImpl<P>();
		for (int i= 0; i < parents.size(); i++){
			if (comparator.compare(parents.get(i), offspring.get(i)) <= 0) {
				result.add(offspring.get(i));
			} else {
				result.add(parents.get(i));
			}
		}
		int size =  getNewPopulationSize(parents,offspring);
		result = new PopulationImpl<P>(result.subList(0, size)) ;
		return result;
	}
	
	protected int getNewPopulationSize(Population<P> parents, Population<P> offsprings) {
		return parents.size();
	}
}
