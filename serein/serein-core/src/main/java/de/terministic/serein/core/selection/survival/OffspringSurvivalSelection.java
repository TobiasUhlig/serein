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
package de.terministic.serein.core.selection.survival;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SurvivorSelection;
import de.terministic.serein.core.PopulationImpl;

public class OffspringSurvivalSelection<P> implements SurvivorSelection<P> {

	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		// Thanks, but I need no information from reference
	}



	@Override
	public Population<P> selectSurvivors(Population<P> currentPopulation, Population<P> offspring) {
		return new PopulationImpl<P>(offspring);
	}

}
