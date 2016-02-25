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

public interface SurvivorSelection<P> extends EvolutionObject<P> {

	/**
	 * Selects the individuals for the next generation/step of the evolutionary
	 * algorithm.
	 * 
	 * @param currentPopulation
	 *            the individuals belonging to the current population
	 * @param offspring
	 *            the newly generated offspring
	 * @return the next population
	 */
	Population<P> selectSurvivors(Population<P> parents, Population<P> offspring);
}
