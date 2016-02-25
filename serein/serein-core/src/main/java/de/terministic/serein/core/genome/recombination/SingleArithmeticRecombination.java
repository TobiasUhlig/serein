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

import de.terministic.serein.core.genome.DoubleGenome;

public class SingleArithmeticRecombination extends PairRecombination<DoubleGenome> {
	final double dominance;
	
	public SingleArithmeticRecombination(double dominance) {
		if (dominance < 0.0 || dominance > 1.0) throw new OutOfRangeException(dominance, 0.0, 1.0);
		this.dominance = dominance;
	}
	
	@Override
	public DoubleGenome recombine(DoubleGenome g1, DoubleGenome g2, Random random) {
		int index = random.nextInt(g1.size());
		List<Double> genes = new ArrayList<>(g1.getGenes());
		double value = dominance * g1.getGenes().get(index) + (1-dominance) * g2.getGenes().get(index);
		genes.set(index, value);
		// TODO Auto-generated method stub
		return g1.createInstance(genes);
	}

}
