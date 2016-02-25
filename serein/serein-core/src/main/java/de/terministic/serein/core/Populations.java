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
package de.terministic.serein.core;

import java.util.Random;

import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;


/**
 * Provides some convenience methods to generate Populations.
 * 
 * @author tobias uhlig
 * 
 */
public class Populations {

	/**
	 * Generates an empty population using the default implementation of Population interface. 
	 * @return an empty Population
	 */
	public static <P> Population<P> emptyPopulation(){
		return new PopulationImpl<P>();
	}
	
	/**
	 * Pairs two individual in an Population. 
	 * 
	 * @param i1 first Individual
	 * @param i2 second Individual
	 * @return a Population containing the two input Individuals
	 */
	public static <P> Population<P> Pair(Individual<P,?> i1, Individual<P,?> i2) {
		Population<P> pair = emptyPopulation();
		pair.add(i1);
		pair.add(i2);
		return null;
	}
	
	public static <P> Population<P> generatePopulation(Population<P> population, int size, Random random) {
		Population<P> result = new PopulationImpl<P>();
		int index = 0;
		int maxIndex = population.size();
		while (result.size() < size) {
			result.add(population.get(index).getRandomOffspring(random));
			index++;
			if (index >= maxIndex) {
				index = 0;
			}
		}
		return result;
	}

	public static <P> Population<P> generatePopulation(Individual<P, ?> individual, int size, Random random) {
		Population<P> population = new PopulationImpl<P>();
		population.add(individual);
		return generatePopulation(population, size, random);
	}

	public static <P> Population<P> generatePopulation(Individual<P, ?> individual, int size) {
		return generatePopulation(individual, size, new Random());
	}

	public static <P> Population<P> generatePopulation(Population<P> population, int size) {
		return generatePopulation(population, size, new Random());
	}

}
