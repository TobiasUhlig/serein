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
package de.terministic.serein.core.genome.mutation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.terministic.serein.api.Genome;
import de.terministic.serein.api.Mutation;

/**
 * Combination of multiple mutations. For each mutation one mutation operator is
 * chosen with equal probabilty.
 * 
 * @author tobias
 *
 * @param <G>
 *            Genotype
 */
public class CombinationMutation<G extends Genome<?>> implements Mutation<G> {
	private final List<Mutation<G>> mutations;

	public CombinationMutation(List<Mutation<G>> mutationOperators) {
		this.mutations = mutationOperators;
	}

	@SafeVarargs
	public CombinationMutation(Mutation<G>... mutationOperators) {
		this(Arrays.asList(mutationOperators));
	}

	public G mutate(G genome, Random random) {
		int i = random.nextInt(mutations.size());
		return mutations.get(i).mutate(genome, random);

	}

}
