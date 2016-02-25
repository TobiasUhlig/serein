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
package de.terministic.serein.core.selection.individual;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Population;

public class DummySelection<P> implements IndividualSelection<P> {

	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Population<P> selectIndividuals(Population<P> population) {
		return population;
	}
	
	
	
	/**
	 * @throws UsupportedOperationException
	 */
	@Override
	public Individual<P, ?> selectIndividual(Population<P> population) {
		throw new UnsupportedOperationException("DummySelection cannot select a single individual from a population");
	}

}
