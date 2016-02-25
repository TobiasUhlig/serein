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

import de.terministic.serein.core.genome.DoubleGenome;

public class LocalRecombination extends PairRecombination<DoubleGenome> {

	public LocalRecombination() {

	}

	@Override
	DoubleGenome recombine(DoubleGenome g1, DoubleGenome g2, Random random) {
		List<Double> genes = new ArrayList<>(g1.getGenes());
		for (int i = 0; i < g1.size(); i++) {
			double alpha = random.nextDouble();
			double value = alpha * g1.getGenes().get(i)
					+ (1 - alpha) * g2.getGenes().get(i);
			genes.set(i, value);
		}
		return g1.createInstance(genes);
	}

}
