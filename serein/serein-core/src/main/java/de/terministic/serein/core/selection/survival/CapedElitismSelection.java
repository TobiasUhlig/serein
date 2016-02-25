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

import java.util.Collections;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SurvivorSelection;
import de.terministic.serein.core.PopulationImpl;

public class CapedElitismSelection<P> implements SurvivorSelection<P> {
	private final FitnessComparator<P> comparator;
	private final double capValue; 
	EvolutionEnvironment<P> environment;
	
	public CapedElitismSelection(FitnessComparator<P> comparator, double capValue) {
		this.comparator = comparator;
		this.capValue = capValue;
		
	}
	

	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		this.environment= referenceEa;
	}

	@Override
	public Population<P> selectSurvivors(Population<P> parents, Population<P> offspring) {
		Population<P> currentPopulation = environment.getPopulation();
		int size =  getNewPopulationSize(currentPopulation,offspring);
		int cap = (int) (capValue * size);
		
		
		Population<P> result = new PopulationImpl<P>(offspring);
		Population<P> current = new PopulationImpl<P>(currentPopulation);
		Collections.sort(current, Collections.reverseOrder(comparator));
		//System.out.println(comparator.fitnessToString(Collections.max(currentPopulation, comparator))+"\n ->	"+ comparator.fitnessToString(current.get(0)));
		result.addAll(new PopulationImpl<P>(current.subList(0, cap)));
		// we want the fittest at the beginning of the list and therefore reverse the comparator order
		Collections.sort(result, Collections.reverseOrder(comparator));
		
		result = new PopulationImpl<P>(result.subList(0, size)) ;
		return result;
	}
	
	protected int getNewPopulationSize(Population<P> currentPopulation, Population<P> offsprings) {
		return currentPopulation.size();
	}
	
	public String toString(){
		return capValue + "-elitism selection";
	}
}
