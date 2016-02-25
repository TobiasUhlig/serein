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
package de.terministic.serein.core.selection.individual;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Population;
import de.terministic.serein.core.PopulationImpl;

public class MateSelection<P> implements IndividualSelection<P> {
	final String					genomId;
	final IndividualSelection<P>	innerSelector;



	public MateSelection(String genomId, IndividualSelection<P> selector) {
		this.genomId 		= genomId;
		this.innerSelector  = selector;
	}



	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		innerSelector.setEnvironment(referenceEa);
	}



	@Override
	public Population<P> selectIndividuals(Population<P> population) {
		Population<P> mates = new PopulationImpl<P>();
		for (Individual<P, ?> individual : population) {
			if (individual.getGenome().getGenomeId().equals(genomId)) {
				mates.add(individual);
			}
		}
		return mates;
	}



	@Override
	public Individual<P, ?> selectIndividual(Population<P> population) {
		return innerSelector.selectIndividual(selectIndividuals(population));
	}
}
