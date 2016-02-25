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
package de.terministic.serein.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;


/**
 * A basic implementation of a population using the Java standard
 * implementation ArrayList.
 * 
 * @author tobias uhlig
 * 
 * @param <P>
 *            the kind of phenotype of the individuals in the population.
 */
public class PopulationImpl<P> extends ArrayList<Individual<P, ?>> implements
		Population<P> {

	private static final long serialVersionUID = -5599597995414998214L;

	public PopulationImpl() {
		super();
	}

	public PopulationImpl(List<Individual<P, ?>> list) {
		super(list);
	}

	public PopulationImpl(Population<P> population) {
		super(population);
	}

	@Override
	public Individual<P, ?> getFittest(FitnessComparator<P> fitnessComparator) {
		return Collections.max(this, fitnessComparator);
	}

}
