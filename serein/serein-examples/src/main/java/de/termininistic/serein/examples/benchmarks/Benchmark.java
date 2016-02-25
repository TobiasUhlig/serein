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
package de.termininistic.serein.examples.benchmarks;

import java.util.Random;

import org.apache.commons.math3.linear.RealVector;

import de.termininistic.serein.examples.benchmarks.functions.BenchmarkFunction;
import de.termininistic.serein.examples.benchmarks.functions.multimodal.RastriginFunction;
import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.Mutation;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.Recombination;
import de.terministic.serein.api.TerminationCondition;
import de.terministic.serein.core.AlgorithmFactory;
import de.terministic.serein.core.BasicIndividual;
import de.terministic.serein.core.Populations;
import de.terministic.serein.core.StatsListener;
import de.terministic.serein.core.genome.DoubleGenome;
import de.terministic.serein.core.genome.mutation.SinglePointMutation;
import de.terministic.serein.core.genome.recombination.UniformCrossover;
import de.terministic.serein.core.genome.translators.LinearTranslator;
import de.terministic.serein.core.selection.individual.TournamentSelection;
import de.terministic.serein.core.termination.TerminationConditionFitness;

public class Benchmark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Benchmark parameters
		BenchmarkFunction function = new RastriginFunction();
		int dimension 			= 10; 
		double optimum 			= function.map(function.getOptimum(dimension));
		double absoluteError 	= 1.0e-2;
		
		// Metaheuristic Parameters
		Random random 									= new Random(1233);
		int populationSize 								= 100;
		Recombination<DoubleGenome> recombination 		= new UniformCrossover<>(); 
		Mutation<DoubleGenome> mutation 				= new SinglePointMutation<>();
		double recombinationProbability 				= 1.0;
		BenchmarkFunctionFitness fitness 				= new BenchmarkFunctionFitness(function);
		TerminationCondition<RealVector> termination 	= new TerminationConditionFitness<>(fitness, optimum + absoluteError); 
		
		// Translator doubleGenome --> function domain
		LinearTranslator translator = new LinearTranslator(function.getDomain().getMinVector(dimension), function.getDomain().getMaxVector(dimension) ); 
		
		// Initial individual 
		DoubleGenome initialGenome = DoubleGenome.createRandomDoubleGenome(dimension, random);	
		BasicIndividual<RealVector, DoubleGenome> initialIndividual = new BasicIndividual<RealVector, DoubleGenome>(initialGenome, translator);		
		initialIndividual.setRecombination(recombination);
		initialIndividual.<Double>setProperty("ProbabilityOfRecombination", recombinationProbability, true);
		initialIndividual.setMutation(mutation);
		initialIndividual.setMateSelection(new TournamentSelection<>(fitness, 3));		

		// Start population
		Population<RealVector> startPop = Populations.generatePopulation(initialIndividual, populationSize, random);

		// Assemble Metaheuristic 
		AlgorithmFactory<RealVector> factory = new AlgorithmFactory<>();
		factory.termination = termination;		
		EvolutionEnvironment<RealVector> algo = factory.createReferenceEvolutionaryAlgorithm(fitness, startPop, random);
		
		// Add a simple listener (output to console)
		StatsListener<RealVector> listener = new StatsListener<RealVector>(fitness, 50);
		algo.addListener(listener);
		
		// Start optimization
		algo.evolve();

	}

}
