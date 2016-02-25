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

import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.FitnessFunction;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.TerminationCondition;
import de.terministic.serein.core.selection.individual.DummySelection;
import de.terministic.serein.core.selection.individual.TournamentSelection;
import de.terministic.serein.core.selection.survival.AllSurviveSelection;
import de.terministic.serein.core.selection.survival.OffspringSurvivalSelection;
import de.terministic.serein.core.selection.survival.SimulatedAnnealingSelection;
import de.terministic.serein.core.selection.survival.UncappedElitismSelection;
import de.terministic.serein.core.termination.TerminationConditionGenerations;

public class AlgorithmFactory<P> {
	
	public TerminationCondition<P> termination = new TerminationConditionGenerations<P>(100); 
	
	public AlgorithmFactory(){
		
	}
	
	public EvolutionEnvironment<P> createReferenceEvolutionaryAlgorithm(FitnessComparator<P> fitness, Population<P> population, Random random) {
		DefaultEvolutionEnvironment<P> result = new DefaultEvolutionEnvironment<P>();
		result.setRandom(random);
		result.setReferenceFitnessComparator(fitness);
		result.setTerminationCondition(termination);
		result.setReproductionSelector(new TournamentSelection<P>(fitness, 3));
		result.setSurvivalSelector(new UncappedElitismSelection<P>(fitness));
		result.setStartPopulation(population);
		return result;
	}
	
	
	public EvolutionEnvironment<P> createHunterEvolutionaryAlgorithm(
			FitnessComparator<P> fitness, Population<P> population,
			Random random) {
		DefaultEvolutionEnvironment<P> result = new DefaultEvolutionEnvironment<P>();
		result.setRandom(random);
		result.setReferenceFitnessComparator(fitness);
		result.setTerminationCondition(termination);
		result.setReproductionSelector(new DummySelection<P>());
		result.setSurvivalSelector(new AllSurviveSelection<P>());
		result.setStartPopulation(population);
		return result;
	}
	
	public EvolutionEnvironment<P> createParticleSwarmOptimization(FitnessComparator<P> fitness, Population<P> population, Random random) {
		DefaultEvolutionEnvironment<P> result = new DefaultEvolutionEnvironment<P>();
		result.setRandom(random);
		result.setReferenceFitnessComparator(fitness);
		result.setTerminationCondition(termination);
		result.setReproductionSelector( new DummySelection<P>());
		result.setSurvivalSelector(new OffspringSurvivalSelection<P>());
		result.setStartPopulation(population);
		return result;
	}
	
	public EvolutionEnvironment<P> createParallelSimulatedAnnealing(FitnessFunction<P> fitness, Population<P> population, Random random, double initialTemperature, double coolDownFactor) {
		DefaultEvolutionEnvironment<P> result = new DefaultEvolutionEnvironment<P>();
		result.setRandom(random);
		result.setReferenceFitnessComparator(fitness);
		result.setTerminationCondition(termination);
		result.setReproductionSelector( new DummySelection<P>());
		result.setSurvivalSelector(new SimulatedAnnealingSelection<P>(fitness, initialTemperature, coolDownFactor));
		result.setStartPopulation(population);
		return result;
	}
	
	public EvolutionEnvironment<P> createSimulatedAnnealing(FitnessFunction<P> fitness, Individual<P,?> individual, Random random, double initialTemperature, double coolDownFactor) {
		Population<P> population = Populations.emptyPopulation();
		population.add(individual);	
		return createParallelSimulatedAnnealing(fitness, population, random, initialTemperature, coolDownFactor);
	}
	
	

}
