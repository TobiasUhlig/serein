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

import de.terministic.serein.core.util.Lists;

public class PermutationGenome<E> extends ListGenome<E> {



	public PermutationGenome(List<E> genes) {
		super(genes);
	}


	
	@Override
	public String getGenomeId() {
		return "PermutationGenom " + getGenes().get(0).getClass().getName();
	}



	@Override
	public PermutationGenome<E> createInstance(List<E> genes) {
		if (Lists.isPermutation(this.getGenes(), genes)) {
			return new PermutationGenome<E>(genes);
			
		}
		System.out.println("blob");
		// TODO proper Error Handling
		return null;
	}



	@Override
	public PermutationGenome<E> createRandomInstance(Random random) {
		List<E> result = new ArrayList<E>(getGenes());
		Collections.shuffle(result, random);
		return new PermutationGenome<E>(result);
	}

}
