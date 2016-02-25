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
package de.terministic.serein.api;

public interface IndividualSelection<P> extends EvolutionObject<P> {

	/**
	 * Selects a group of individuals. An individual may be chosen more
	 * than once and therefore occur multiple times in the resulting selection.
	 * The number of chosen parents depends on the implementation.
	 * 
	 * @param population
	 *            the individuals from which the parents are chosen.
	 * @return a population containing individuals that are chosen as parents,
	 *         individuals may be included more than once in this selection.
	 * 
	 */
	Population<P> selectIndividuals(Population<P> population); 



	/**
	 * Selects a single individual from a given population.
	 * 
	 * @param population
	 *            the individuals from which the parents are chosen.
	 * @return a single individual selected for reproduction purposes
	 */
	Individual<P, ?> selectIndividual(Population<P> population);
}
