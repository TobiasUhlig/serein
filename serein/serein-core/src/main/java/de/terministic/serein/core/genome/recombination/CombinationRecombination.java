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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.terministic.serein.api.Genome;

public class CombinationRecombination<G extends Genome<?>> extends PairRecombination<G> {
	
	private final List<PairRecombination<G>> recombinations;
	
	public CombinationRecombination(List<PairRecombination<G>> recombinations) {
		this.recombinations = new ArrayList<PairRecombination<G>>(recombinations);
	}
	@SafeVarargs
	public CombinationRecombination(PairRecombination<G>... recombinations) {
		this(Arrays.asList(recombinations));
	}

	@Override
	G recombine(G g, G g2, Random random) {
		int i = random.nextInt(recombinations.size());
		return recombinations.get(i).recombine(g, g2, random);
	}
	


}
