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

import org.apache.commons.math3.exception.OutOfRangeException;

import de.terministic.serein.api.Genome;
import de.terministic.serein.api.Recombination;
import de.terministic.serein.api.RecombinationException;



public abstract class AbstractRecombination<G extends Genome<?>> implements
		Recombination<G> {

	private double dominance = 0.0;

	@Override
	public G recombine(List<G> genomes, Random random)
			throws RecombinationException {
		if (genomes.size() < 2) {
			throw new RecombinationException(
					"Recombination needs at least two parental genomes as input.");
		} else if (getMaximumSupportedNoGenomes() < genomes.size()
				&& getMaximumSupportedNoGenomes() > 0) {
			throw new RecombinationException(
					"This recombination operator only supports up to "
							+ getMaximumSupportedNoGenomes()
							+ " parental genomes as input.");
		}

		return doRecombination(genomes, random);
	}

	protected abstract G doRecombination(List<G> genomes, Random random)
			throws RecombinationException;

	

	protected double[] getDominance(List<G> genomes) {
		int count = genomes.size();
		double[] result = new double[count];
		double remainder = 1.0;
		if (dominance == 0.0) {
			result[0] = remainder / count;
		} else {
			result[0] = dominance;
			remainder = remainder - dominance;
		}
		for (int i = 1; i < count; i++) {
			result[i] = remainder / count;
		}
		return result;
	}

	/**
	 * Sets a dominance factor used for recombination. 0.0 is used for equally
	 * distributed dominance.
	 * 
	 * 
	 * @param dominance
	 */
	public void setDominance(double dominance) {
		if (dominance > 1.0 || dominance < 0.0)
			throw new OutOfRangeException(dominance, 0.0, 1.0);
		this.dominance = dominance;
	}

}
