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
import java.util.Random;


/**
 * Implements some kind of recombination of more than one (most commonly 2) genomes to derive one new genome;
 * 
 * @author tobias uhlig
 *
 * @param <G> the kind of genome this operator works on.
 */
public interface Recombination<G extends Genome<?>> {
	
	
	/**
	 * Generates a new genome by recombining more than one input genome.
	 * @param genomes a List containing at least two parental genomes.
	 * @param random
	 * @return the generated genome
	 */
	G recombine( List<G> genomes, Random random) throws RecombinationException;
	
	/**
	 * If a value smaller than or equal to zero is returned, unlimited number of
	 * genomes are supported.
	 * 
	 * @return the maximum number of supported genomes for recombination.
	 */
	public abstract int getMaximumSupportedNoGenomes();

}
