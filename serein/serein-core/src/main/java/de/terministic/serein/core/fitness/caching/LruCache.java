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
/*
 * sage - a framework to evaluate Scheduling Heuristics (SH -> sage) for typical
 * scheduling problems in semiconductor manufacturing. It contains a simple
 * discrete event simulator which uses approximation methods to simulate
 * schedules. Schedules can be evaluate according to different objective values.
 * 
 * by Tobias Uhlig - tobias.uhlig@unibw.de
 */
package de.terministic.serein.core.fitness.caching;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A cache holding a number of key-value pairs.
 * 
 * @author tobias uhlig
 * @version $Revision: 1.0 $
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The max size. */
	private final int maxSize;



	/**
	 * Instantiates a new LRU-cache.
	 * 
	 * @param size
	 *            the maximal number of entries this cache can hold
	 */
	public LruCache(final int size) {
		//true --> activates LRU feature
		super((int) (size * 1.35), 0.75F, true);
		maxSize = size;

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
	 */
	@Override
	protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
		return super.size() > maxSize;
	}
	
}
