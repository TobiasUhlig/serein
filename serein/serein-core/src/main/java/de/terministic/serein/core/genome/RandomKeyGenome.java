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
package de.terministic.serein.core.genome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomKeyGenome<E> extends ListGenome<Double> {
	private final List<E> referenceElements;
	private final List<E> sortedElements;

	public RandomKeyGenome(List<E> referenceElements, List<Double> keys) {
		super(keys);
		if (referenceElements.size() != keys.size()) {
			// TODO Error handling
		}
		this.referenceElements = referenceElements;
		sortedElements = getOrderdElements();
	}

	public RandomKeyGenome(List<E> genes, Random random) {
		this(genes, randomKeys(genes.size(), random));
	}

	@Override
	public String getGenomeId() {
		return "RandomKeyGenome" + sortedElements.get(0).getClass().getName();
	}

	public List<E> getOrderedElements() {
		return sortedElements;
	}

	@Override
	public RandomKeyGenome<E> createInstance(List<Double> genes) {
		RandomKeyGenome<E> genome = new RandomKeyGenome<E>(referenceElements, genes);
		return genome;
	}

	@Override
	public RandomKeyGenome<E> createRandomInstance(Random random) {
		return new RandomKeyGenome<E>(referenceElements, random);
	}

	private List<E> getOrderdElements() {
		// do not modify the original representation
		List<Double> reprCopy = new ArrayList<Double>(getGenes());
		List<Double> sortedKeys = new ArrayList<Double>(reprCopy);
		Collections.sort(sortedKeys);
		int l = sortedKeys.size();
		// now find the indices in the original repr and use them for
		// permutation
		List<E> result = new ArrayList<E>(l);

		for (int i = 0; i < l; i++) {
			int index = reprCopy.indexOf(sortedKeys.get(i));
			result.add(referenceElements.get(index));
			reprCopy.set(index, null);
		}
		return result;
	}

	private static List<Double> randomKeys(int l, Random random) {
		List<Double> keys = new ArrayList<Double>(l);
		for (int i = 0; i < l; i++) {
			keys.add(random.nextDouble());
		}
		return keys;
	}

}
