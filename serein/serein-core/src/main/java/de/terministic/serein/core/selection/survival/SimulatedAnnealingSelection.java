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

import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.FitnessFunction;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SurvivorSelection;
import de.terministic.serein.core.Populations;

public class SimulatedAnnealingSelection <P> implements SurvivorSelection<P>{
	private double temperature;
	private double initialTemperature;
	private double coolDownFactor;
	private Random random = new Random();
	private FitnessFunction<P> fitnessFunction;
	
	public SimulatedAnnealingSelection(FitnessFunction<P> fitnessFunction, double initialTemperature, double coolDownFactor){
		this.fitnessFunction = fitnessFunction;
		this.initialTemperature = initialTemperature;
		this.temperature = initialTemperature;
		this.coolDownFactor = coolDownFactor;
		
	}
	
	@Override
	public void setEnvironment(EvolutionEnvironment<P> environment) {
		this.random = new Random(environment.getRandom().nextLong());
		temperature = initialTemperature;	
	}

	@Override
	public Population<P> selectSurvivors(Population<P> parents, Population<P> offspring) {
		Population<P> result = Populations.<P>emptyPopulation();
		for (int i = 0; i < parents.size(); i++){
			result.add(metropolisHastingsSelection(offspring.get(i), parents.get(i)));	
		}
		updateTemperature();
		return result;
	}

	protected void updateTemperature() {
		temperature = temperature * coolDownFactor;	
	}
	
	protected Individual<P, ?> metropolisHastingsSelection(Individual<P, ?> offspring, Individual<P, ?> parent) {
		/*
		 *  Metropolis criterion: P = e^(-((E(s+1) - E(s)) / T));
		 *  Energy [E] = fitness
		 *  State  [s] = individual
		 */
		Individual<P, ?> selected = parent;
		double offspringEnergy = fitnessFunction.getFitness(offspring);
		double parentEnergy = fitnessFunction.getFitness(parent);
		double energyDifference;	
		if (fitnessFunction.isNaturalOrder()) {
			
			energyDifference = offspringEnergy - parentEnergy;
			
		} else {
			energyDifference = parentEnergy - offspringEnergy;
		}
		
		double acceptanceProbability = Math.exp(energyDifference / temperature);
		if (acceptanceProbability > random.nextDouble()) {
			selected = offspring;
		} 	
		return selected;
	}

}
