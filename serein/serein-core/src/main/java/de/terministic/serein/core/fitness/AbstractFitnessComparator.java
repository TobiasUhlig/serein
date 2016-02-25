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
package de.terministic.serein.core.fitness;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.FitnessListener;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;
import de.terministic.serein.core.fitness.caching.FitnessCache;
import de.terministic.serein.core.fitness.caching.LruFitnessCache;

public abstract class AbstractFitnessComparator<P, F extends Comparable<F>> implements FitnessComparator<P> {

	private FitnessCache<P, F> cache = new LruFitnessCache<>();
	private final List<FitnessListener<P>> listeners = new ArrayList<>();
	
	/** Format to limit the digits of double to three. */
	public static final DecimalFormat THREE_DIGITS = new DecimalFormat("#0.000");
	
	@Override
	public void sort(Population<P> population) {
		Collections.sort(population, this);
	}
	
	@Override
	public int compare(Individual<P, ?> individual1, Individual<P, ?> individual2) {
		int result = 0;
		F f1 = getFitness(individual1);
		F f2 = getFitness(individual2);
		result = f1.compareTo(f2);
		update(individual1, individual2, result);
		return result;
	}

	@Override
	public String fitnessToString(Individual<P, ?> individual) {
		return getFitness(individual).toString();
	}

	public F getFitness(Individual<P, ?> individual) {
		F fitness = null;
		if (individual == null) {
			return fitness;
		}
		fitness = cache.get(individual);
		//handling of cache miss
		if (fitness == null) {
			fitness = calculateFitness(individual);
			updateCalculation(individual);
			cache.put(individual, fitness);
		}
		return fitness;
	}
	
	public FitnessCache<P, F> getCache() {
		return cache;
	}

	public void setCache(FitnessCache<P, F> cache) {
		this.cache = cache;
	}
	protected abstract F calculateFitness(Individual<P, ?> individual);
	
	protected void updateCalculation(Individual<P, ?> individual) {
		for(FitnessListener<P> l:listeners) {
			 l.updateFitnessCalculation(individual);
			}
	}
	
	protected void update(Individual<P, ?> individual1, Individual<P, ?> individual2, int decission){
		for(FitnessListener<P> l:listeners) {
		 l.updateComparison(individual1, individual2, decission);
		}
	}
	
	public void addListener(FitnessListener<P> listener){
		listeners.add(listener);
	}
	

	public void removeListener(FitnessListener<P> listener){
		listeners.remove(listener);
	}
	

}
