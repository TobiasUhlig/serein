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

public class LruFitnessCache<P, F extends Comparable<F>> implements FitnessCache<P, F> {
	
	private final LruCache<Long, F> cache;
	public final static int DEFAULT_CACHE_SIZE = 2000;
	
	public LruFitnessCache(int size) {
		this.cache = new LruCache<Long, F>(size);
	}
	
	public LruFitnessCache() {
		this(DEFAULT_CACHE_SIZE);
	}
	
	@Override
	public void put(Individual<P, ?> individual, F fitness) {
		cache.put(individual.getId(), fitness);	
		
	}

	@Override
	public F get(Individual<P, ?> individual) {
		return cache.get(individual.getId());
	}
	
	@Override 
	public void clear() {
		cache.clear();
	}

}
