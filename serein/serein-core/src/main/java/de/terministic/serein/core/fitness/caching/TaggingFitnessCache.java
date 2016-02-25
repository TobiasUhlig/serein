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
package de.terministic.serein.core.fitness.caching;

import de.terministic.serein.api.Individual;



public class TaggingFitnessCache<P, F extends Comparable<F>> implements FitnessCache<P, F> {
	private final String tag; 
	
	public TaggingFitnessCache(String tag) {
		this.tag =  tag;
	
	}
	
	@Override
	public void put(Individual<P, ?> individual, F fitness) {
		individual.<F> setProperty(tag, fitness, false);
		
	}

	@Override
	public F get(Individual<P, ?> individual) {
		F result = null;
		if (individual.<F> hasProperty(tag)) {
			result = individual.<F> getProperty(tag);
		}
		return result;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Tagging Cache cannot be reset.");
	}
	

}
