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

public class SbxRecombination extends PairRecombination<DoubleGenome> {
	
	private final double eta;
	
	public SbxRecombination(double eta) {
		this.eta = eta;

	}
	
	public SbxRecombination() {
		this(2.0);
	}
	
	@Override
	DoubleGenome recombine(DoubleGenome g1, DoubleGenome g2, Random random) {
		List<Double> genes = new ArrayList<>(g1.getGenes());
		
		for (int i = 0; i < g1.size(); i++) {
	
			double u = random.nextDouble();
			double beta;
				
			if (u <= 0.5) {
				beta = Math.pow(2 * u, 1.0 / (eta +1));
			} else {
				beta = Math.pow(1 / (2 * (1.0 -u)), 1.0/(eta + 1));
			} 
			double x1 = g1.getGenes().get(i);
			double x2 = g2.getGenes().get(i);
			double value = 0.5 * ((1 + beta) * x1 + (1 - beta) * x2);
			genes.set(i, value);
			DoubleGenome.limitToBounds(genes);
		}
		return g1.createInstance(genes);
	}

}
