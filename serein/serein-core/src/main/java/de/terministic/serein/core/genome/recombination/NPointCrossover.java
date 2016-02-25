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
import java.util.Collections;
import java.util.List;
import java.util.Random;

import de.terministic.serein.core.genome.ListGenome;

public class NPointCrossover<G extends ListGenome<?>> extends PairRecombination<G> {
	private final int n;

	public NPointCrossover(int n) {
		this.n = n;
	}

	public NPointCrossover() {
		this(3);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	G recombine(G g1, G g2, Random random) {
		ArrayList<Integer> xPoints = new ArrayList<>();
		xPoints.add(0);
		xPoints.add(g1.size());
		while (xPoints.size() < n + 2) {
			int p = random.nextInt(g1.size());
			if (!xPoints.contains(p)) {
				xPoints.add(p);
			}
		}
		Collections.sort(xPoints);
		List result = new ArrayList<>();
		for (int i = 0; i < xPoints.size() - 1; i++) {

			if (i % 2 == 0) {
				result.addAll(g1.getGenes().subList(xPoints.get(i), xPoints.get(i + 1)));
			} else {
				result.addAll(g2.getGenes().subList(xPoints.get(i), xPoints.get(i + 1)));
			}
		}
		return (G) g1.createInstance(result);
	}

}