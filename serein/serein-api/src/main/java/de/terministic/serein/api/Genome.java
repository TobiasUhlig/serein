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
 * The Genome represents the entirety of an Individuals hereditary information.
 * Basically its a container for information encoding a candidate solution.
 * Therefore an instance of the genome is the genotype of an individual from
 * which its phenotype can be derived.<br>
 * 
 * Different implementation of a genome can be based on different genetic codes.
 * Each type of genome has an identifier, making is possible to test whether an
 * individual with a certain genome is compatible to the genome of another 
 * individual.
 * 
 * @author tobias uhlig
 * 
 * @param <T>
 *            Type of the genetic information representation used to generate an
 *            instance of this genome. Basically the type of its genes.
 */
public interface Genome<T> {

	/**
	 * An identifier to discern the Type of Genome represented in the
	 * implementing class. This is used to test compatibility of genotypes of
	 * individuals when recombining individuals.
	 * 
	 * @return identifier of the Genome
	 */
	public String getGenomeId();



	/**
	 * Creates a genome object which has a defined genotype given by the genes.
	 * 
	 * @param genes
	 *            the information needed to generate an unique instance of a
	 *            genome.
	 * @return a genome generated using the input genes.
	 */
//	public <G extends Genome<T>> G createInstance(T genes);
	public Genome<T> createInstance(T genes);



	/**
	 * Creates a genome object which has a random genotype.
	 * 
	 * @param random
	 *            a random number generator
	 * @return a genome with an random genotype
	 */
	public Genome<T> createRandomInstance(Random random);
	
	/**
	 * 
	 * @return the List containing the genes.
	 */
	public T getGenes();
}
