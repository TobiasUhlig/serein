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

import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Population;
import de.terministic.serein.core.Populations;

public abstract class AbstractIndividualSelection<P> implements IndividualSelection<P> {
	private int numberToSelect;
	protected Random random = new Random();
	protected FitnessComparator<P> comparator;
	protected EvolutionEnvironment<P> referenceEA;



	@Override
	public void setEnvironment(EvolutionEnvironment<P> environment) {
		if (environment != null){
			// use reference to initialize random number generator
			this.random = new Random(environment.getRandom().nextLong());
			this.referenceEA = environment;
		}
		
	}



	@Override
	public Population<P> selectIndividuals(Population<P> population) {
		Population<P> selected = Populations.emptyPopulation();

		for (int i = 0; i < getNumberToSelect(population); i++) {
			selected.add(this.selectIndividual(population));
		}

		return selected;
	}



	protected int getNumberToSelect(Population<P> population) {
		int count = numberToSelect;

		if (numberToSelect == 0) {
			count = population.size();
		}

		return count;
	}



	/**
	 * 
	 * @return the number of parents selected during each selection process. A
	 *         zero has a special meaning of automated 
	 */
	public int getNumberToSelect() {
		return numberToSelect;
	}



	/**
	 * Sets the number of parents select during each call to selectParents. If
	 * an value is set which is not an positive integer the number of selected
	 * parents is chosen automatically. In this case the number is equal to the
	 * size of the population from which the parents are chosen.
	 * 
	 * @param parentCount
	 *            the number of parents to select
	 * @return true if the value was accepted otherwise an automatic value zero is
	 *         used
	 */
	public boolean setNumberToSelect(int parentCount) {
		boolean accepted;
		if (parentCount > 0) {
			this.numberToSelect = parentCount;
			accepted = true;
		} else {
			this.numberToSelect = 0;
			accepted = false;
		}
		return accepted;

	}

}
