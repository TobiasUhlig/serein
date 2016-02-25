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
package de.termininistic.serein.examples.tsp;

import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Mutation;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.TerminationCondition;
import de.terministic.serein.core.AlgorithmFactory;
import de.terministic.serein.core.BasicIndividual;
import de.terministic.serein.core.Populations;
import de.terministic.serein.core.StatsListener;
import de.terministic.serein.core.genome.PermutationGenome;
import de.terministic.serein.core.genome.mutation.SwapMutation;
import de.terministic.serein.core.selection.individual.TournamentSelection;
import de.terministic.serein.core.termination.TerminationConditionTime;

public class Tsp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Traveling salesperson (TSP) parameters
		Tour tour = ProblemParser.TourFromFile("tsplib/a280.tsp.gz");
		
		// Metaheuristic Parameters
		Random random 												= new Random(1233);
		int populationSize 											= 10;
		Mutation<PermutationGenome<Location>> mutation 				= new SwapMutation<>();
		TourFitness fitness 										= new TourFitness();
		TerminationCondition<Tour> termination 						= new TerminationConditionTime<>(1000); 

		// Initial individual 
		PermutationGenome<Location> g = new PermutationGenome<Location>(tour);
		BasicIndividual<Tour, PermutationGenome<Location>> initialIndividual = new BasicIndividual<>(g, new TourTranslator());
		initialIndividual.setMutation(mutation);
		initialIndividual.setMateSelection(new TournamentSelection<>(fitness, 3));

		// startpopulation
		Population<Tour> startPop = Populations.generatePopulation(initialIndividual, populationSize, random);

		// assembling the metaheuristic
		AlgorithmFactory<Tour> factory = new AlgorithmFactory<>();
		factory.termination = termination;	
		EvolutionEnvironment<Tour> algo = factory.createReferenceEvolutionaryAlgorithm(fitness, startPop, random);
		
		StatsListener<Tour> listener = new StatsListener<Tour>(fitness, 10);
		algo.addListener(listener);

		// run optimization
		algo.evolve();

		// result
		tour = algo.getFittest().getPhenotype();
	}

}
