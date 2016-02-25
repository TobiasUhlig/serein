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

import java.util.Collections;

import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;
import de.terministic.serein.core.PopulationImpl;
import de.terministic.serein.core.Populations;

public class TournamentSelection<P> extends AbstractIndividualSelection<P> {
	private final int tour;

	public TournamentSelection(FitnessComparator<P> comparator, int tournamentSize) {
		this.comparator = comparator;
		this.tour = tournamentSize;
	}

	@Override
	public Individual<P, ?> selectIndividual(Population<P> population) {
		Individual<P, ?> result;
		Population<P> tournament = new PopulationImpl<P>();
		int size = population.size();
		for (int i = 0; i < tour; i++) {
			tournament.add(population.get(random.nextInt(size)));
		}
		result = Collections.max(tournament, comparator);
		return result;
	}
	
//	@Override
//	public Individual<P, ?> selectIndividual(Population<P> population) {
//		Individual<P, ?> result;
//		Population<P> sorted = new PopulationImpl<P>(population);
//		Collections.sort(sorted, Collections.reverseOrder(comparator));
//		int size = sorted.size();
//		int index = size;
//		for (int i = 0; i < tour; i++) {
//			index = Math.min(index, random.nextInt(size));
//
//		}
//		result = sorted.get(index);
//		return result;
//	}
	
	@Override
	public Population<P> selectIndividuals(Population<P> population) {
		Population<P> selected = Populations.emptyPopulation();
		Population<P> sorted = new PopulationImpl<P>(population);
		Collections.sort(sorted, Collections.reverseOrder(comparator));
		for (int c = 0; c < getNumberToSelect(population); c++) {
			int size = sorted.size();
			int index = size;
			for (int i = 0; i < tour; i++) {
				index = Math.min(index, random.nextInt(size));

			}
			selected.add(sorted.get(index));
		}

		return selected;
	}
	
	public String toString(){
		return tour+"-tournament selection";
	}
	

}
