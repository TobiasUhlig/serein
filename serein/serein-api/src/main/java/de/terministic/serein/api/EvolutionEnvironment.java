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

import java.util.Random;

/**
 * @author tobias uhlig
 * 
 *         An evolution environment is the context for an evolutionary process.
 * 
 * @param <P>
 *            Phenotype - the kind of candidate solution represented by the
 *            individuals in this environment
 * 
 */
public interface EvolutionEnvironment<P> {

	/**
	 * This method evolves the current population. Depending on the
	 * implementation a single evolution step may be performed or multiple
	 * generations are used for evolution until a certain termination criterion
	 * is meet.
	 */
	void evolve();

	/**
	 * Returns the fittest individual using the reference fitness comparator,
	 * including the history of all individuals.
	 * 
	 * @return the fittest indidvidual.
	 */
	Individual<P, ?> getFittest();

	/**
	 * Provides a reference fitness comparator, that may be used by other
	 * objects/individuals.
	 * 
	 * @return a fitness comparator
	 */
	FitnessComparator<P> getReferenceFitnessComparator();

	/**
	 * Returns the current population which is either the initial population at
	 * the beginning of the algorithm or the group of individuals selected from
	 * previous populations and newly generated offspring.
	 * 
	 * @return the current working population of the algorithm
	 */
	Population<P> getPopulation();

	/**
	 * Set up the initial individuals for the evolutionary algorithm.
	 * 
	 * @param population
	 *            the initial individuals
	 */
	void setStartPopulation(Population<P> population);

	/**
	 * Adds a listener to the evolutionary algorithm.
	 * 
	 * @param listener
	 */
	void addListener(EvolutionListener<P> listener);

	/**
	 * Removes a listener from the evolutionary algorithm;
	 * 
	 * @param listener
	 */
	void removeListener(EvolutionListener<P> listener);

	/**
	 * Sets the employed random number generator which is used as the source of
	 * randomness for this algorithm.
	 * 
	 * @param rng
	 */
	void setRandom(Random rng);

	/**
	 * Provides access to the random number generator used by the algorithm as a
	 * source of randomness.
	 * 
	 * @return a random number generator
	 */
	Random getRandom();

}
