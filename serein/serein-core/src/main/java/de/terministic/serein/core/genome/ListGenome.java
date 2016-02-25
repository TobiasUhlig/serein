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

import de.terministic.serein.api.Genome;



public abstract class ListGenome<E> implements Genome<List<E>> {
	private final List<E> genes;



	public ListGenome(List<E> genes) {
		this.genes = Collections.unmodifiableList(genes);
	}

	@Override
	public List<E> getGenes() {
		return new ArrayList<E>(genes);
	}
	
	public int size(){
		return genes.size();
	}

}
