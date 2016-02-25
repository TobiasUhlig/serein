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
 * A mutation operator generates an imperfect copy of an genome with its mutate
 * method. The resulting genotype differs only slightly from the original
 * genome, the difference is refered to as an mutation.
 * 
 * @author tobias uhlig
 * 
 * @param <G>
 *            Type of Genome this Mutation can be applied to.
 */
public interface Mutation<G extends Genome<?>> {

	/**
	 * Generates a new genome as copy of the input genome and applies some kind
	 * of mutation to it resulting in an imperfect replication of the genome.
	 * 
	 * @param genome
	 *            the original genome
	 * @param random
	 *            a random number generator to
	 * @return an imperfect (mutated) copy of the input genome
	 */
	public G mutate(G genome, Random random);
}
