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

import java.util.List;
import java.util.Random;

import de.terministic.serein.api.Mutation;
import de.terministic.serein.core.genome.ListGenome;
import de.terministic.serein.core.util.Lists;

public class SwapMutation<G extends ListGenome<?>> implements Mutation<G> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public G mutate(G genome, Random random) {
		List genes = genome.getGenes();
		Lists.randomSwap(genes, random);
		return (G) genome.createInstance(genes);
	}

}
