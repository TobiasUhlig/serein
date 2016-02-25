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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.terministic.serein.api.Mutation;
import de.terministic.serein.core.genome.DoubleGenome;

public class SinglePointGaussianMutation implements Mutation<DoubleGenome> {

	private double intensity;
	
	public SinglePointGaussianMutation(double intensity) {
		this.intensity = intensity;
	}
	
	public SinglePointGaussianMutation() {
		this(0.05);
	}

	@Override
	public DoubleGenome mutate(DoubleGenome genome, Random random) {
		List<Double> genes =  new ArrayList<>(genome.getGenes());
		int index = random.nextInt(genes.size());
		double value = genome.getGenes().get(index) + intensity * random.nextGaussian();
		if (value < DoubleGenome.LOWER_BOUND) {
			value = DoubleGenome.LOWER_BOUND;
		}
		if (value > DoubleGenome.UPPER_BOUND) {
			value = DoubleGenome.UPPER_BOUND;
		}
		genes.set(index, value);
		return genome.createInstance(genes);
	}
}
