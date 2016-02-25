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
package de.terministic.serein.api;

import java.util.Comparator;

/**
 * A comparator that allows to qualitatively compare two individuals according
 * to their fitness.
 * 
 * @author tobias uhlig
 * 
 * @param <P>
 *            Phenotype - the kind of candidate solution represented by the
 *            individual evaluated by this comparator
 */
public interface FitnessComparator<P> extends Comparator<Individual<P, ?>>, FitnessSorter<P>{

	
	/**
	 * Compares its two individuals according to their fitness. Returns a
	 * negative integer, zero, or a positive integer as the first individual is
	 * less fit, equally fit, or fitter than the second.
	 */
	
	
	public int compare(Individual<P, ?> arg0, Individual<P, ?> arg1);

	/**
	 * Returns a human readable representation of the fitness of an given
	 * individual.
	 * 
	 * @param individual
	 *            the evaluated individual
	 * @return A textual fitness representation
	 */
	String fitnessToString(Individual<P, ?> individual);
}
