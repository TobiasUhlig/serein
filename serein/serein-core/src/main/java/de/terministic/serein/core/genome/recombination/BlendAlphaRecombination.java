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

public class BlendAlphaRecombination extends PairRecombination<DoubleGenome> {
	final double alpha;

	public BlendAlphaRecombination(double alpha) {	
		this.alpha = alpha;
	}

	/**
	 * 
	 * 
	 */
	public BlendAlphaRecombination() {
		this(0.366); //Takahashi and Kita 
	}

	@Override
	DoubleGenome recombine(DoubleGenome g1, DoubleGenome g2, Random random) {
		List<Double> genes = new ArrayList<>(g1.getGenes());
		
		for (int i = 0; i < g1.size(); i++) {
			double max = Math.max(g1.getGenes().get(i), g2.getGenes().get(i));
			double min = Math.min(g1.getGenes().get(i), g2.getGenes().get(i));	
			double interval =  max - min; 
			double xMin = min - alpha * interval; 
			double xMax	= max + alpha * interval;
			double value = xMin + random.nextDouble()*(xMax - xMin);
			genes.set(i, value);
		}
		DoubleGenome.limitToBounds(genes);
		return g1.createInstance(genes);
	}

}
