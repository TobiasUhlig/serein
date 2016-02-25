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

public interface FitnessFunction<P> extends FitnessComparator<P> {

	/**
	 * Calculates a fitness value for an individual.
	 * 
	 * @param individual
	 *            to evaluate
	 * @return the fitness value
	 */
	public Double getFitness(Individual<P, ?> individual);

	/**
	 * Indicates whether the fitness values have natural or non-natural order.
	 * 
	 * @return True if higher fitness values indicate fitter individuals, false
	 *         otherwise.
	 */
	public boolean isNaturalOrder();

}
