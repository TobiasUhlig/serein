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

import de.terministic.serein.core.genome.ListGenome;

public class SinglePointCrossover<G extends ListGenome<?>> extends PairRecombination<G> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	G recombine(G g1, G g2, Random random) {
		int xPoint = random.nextInt(g1.size());
		List result = g1.getGenes().subList(0, xPoint);
		result.addAll(g2.getGenes().subList(xPoint, g2.size()));
		return (G) g1.createInstance(result);
	}

}
