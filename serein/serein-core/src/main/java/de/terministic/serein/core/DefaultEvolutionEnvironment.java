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

import de.terministic.serein.api.EvolutionListener.Msg;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;

public class DefaultEvolutionEnvironment<P> extends
		AbstractEvolutionEnvironment<P> {

	@Override
	public void evolve() {
		update(Msg.StartEvolution);
		while (getTerminationCondition().doContinue()) {
			update(Msg.NextGeneration);
			
			Population<P> parents = getReproductionSelector()
					.selectIndividuals(getPopulation());
			Population<P> offspring = Populations.emptyPopulation();
			for (Individual<P, ?> p : parents) {
				offspring.add(p.getOffspring());
			}

			this.setPopulation(getSurvivalSelector().selectSurvivors(
					parents, offspring));
		}
		update(Msg.FinishedEvolution);
	}
}
