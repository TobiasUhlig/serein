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
package de.terministic.serein.core.genome.recombination;

import java.util.List;
import java.util.Random;

import de.terministic.serein.api.Genome;
import de.terministic.serein.api.Recombination;
import de.terministic.serein.api.RecombinationException;

/**
 * Abstract class for classical recombination operators that combine two
 * genomes.
 * 
 * @author tobias
 *
 * @param <G>
 */
public abstract class PairRecombination<G extends Genome<?>> implements Recombination<G> {

	@Override
	public G recombine(List<G> genomes, Random random) throws RecombinationException {
		if (genomes.size() != 2) {
			throw new RecombinationException("Two genomes were expected for this recombintion operator");
		}
		return recombine(genomes.get(0), genomes.get(1), random);
	}

	abstract G recombine(G g1, G g2, Random random);

	@Override
	public int getMaximumSupportedNoGenomes() {
		return 2;
	}
}
