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

import java.util.List;

/**
 * A population is a group of individuals with the same kind of phenotype,
 * meaning they all represent a candidate solution to the same kind of problem.
 * 
 * 
 * @author tobias uhlig
 * 
 * @param <P>
 *            is the expected kind of phenotype of the individuals in this
 *            population.
 */
public interface Population<P> extends List<Individual<P, ?>> {
	

	/**
	 * Returns the fittest individual of the population according to a certain
	 * fitness function.
	 * 
	 * @param fitnessComparator
	 *            a comparator to evaluate the individuals in the population;
	 * @return the fittest individual
	 */
	Individual<P, ?> getFittest(FitnessComparator<P> fitnessComparator);
}
