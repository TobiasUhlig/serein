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
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.exception.OutOfRangeException;

import de.terministic.serein.core.genome.ListGenome;

public class UniformCrossover<G extends ListGenome<?>> extends PairRecombination<G> {

	final double dominance;

	public UniformCrossover(double dominance) {
		if (dominance < 0.0 || dominance > 1.0)
			throw new OutOfRangeException(dominance, 0.0, 1.0);
		this.dominance = dominance;
	}

	public UniformCrossover() {
		this(0.5);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	G recombine(G g1, G g2, Random random) {
		List result = new ArrayList();
		for (int i = 0; i < g1.size(); i++) {
			if (random.nextDouble() < dominance) {
				result.add(g1.getGenes().get(i));
			} else {
				result.add(g2.getGenes().get(i));
			}
		}
		return (G) g1.createInstance(result);
	}


}
