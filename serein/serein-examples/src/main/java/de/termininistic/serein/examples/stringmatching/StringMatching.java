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
package de.termininistic.serein.examples.stringmatching;

import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Mutation;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.Recombination;
import de.terministic.serein.api.TerminationCondition;
import de.terministic.serein.core.AlgorithmFactory;
import de.terministic.serein.core.BasicIndividual;
import de.terministic.serein.core.Populations;
import de.terministic.serein.core.StatsListener;
import de.terministic.serein.core.genome.StringGenome;
import de.terministic.serein.core.genome.mutation.SinglePointMutation;
import de.terministic.serein.core.genome.recombination.UniformCrossover;
import de.terministic.serein.core.selection.individual.TournamentSelection;
import de.terministic.serein.core.termination.TerminationConditionFitness;



public class StringMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Optimization challenge - match the following message
		String msg = "Methinks it is like a weasel.";
		
		// Metaheuristic Parameters
		Random random 									= new Random(1233);
		int populationSize 								= 3;
		Recombination<StringGenome> recombination 		= new UniformCrossover<>(); 
		Mutation<StringGenome> mutation 				= new SinglePointMutation<>();
		double recombinationProbability 				= 0.5;
		StringFitness stringFitness 					= new StringFitness(msg);
		TerminationCondition<String> termination 	= new TerminationConditionFitness<>(stringFitness, 0.0); 
		
		// Initial individual 
		StringGenome initialGenome = new StringGenome(msg, StringGenome.LETTERS+" .");
		BasicIndividual<String, StringGenome> initialIndividual = new BasicIndividual<>(initialGenome, new StringTranslator());		
		initialIndividual.setRecombination(recombination);
		initialIndividual.setMutation(mutation);
		initialIndividual.<Double>setProperty("ProbabilityOfRecombination", recombinationProbability, true);
		initialIndividual.setMateSelection(new TournamentSelection<>(stringFitness, 3));
		
		
	
		// Start population
		Population<String> startPop = Populations.generatePopulation(initialIndividual, populationSize, random);

		// Assemble Metaheuristic 
		AlgorithmFactory<String> factory = new AlgorithmFactory<>();
		factory.termination = termination;
		EvolutionEnvironment<String> algo = factory.createReferenceEvolutionaryAlgorithm(stringFitness, startPop, random);
		
		// Add a simple listener (output to console)
		StatsListener<String> listener = new StatsListener<>(stringFitness, 50);
		algo.addListener(listener);
		// Start optimization
		algo.evolve();
		
	}
}
