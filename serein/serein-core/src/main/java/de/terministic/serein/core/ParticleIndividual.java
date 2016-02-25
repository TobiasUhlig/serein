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

import java.util.HashMap;

import de.terministic.serein.api.Genome;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SereinException;
import de.terministic.serein.api.Translator;
import de.terministic.serein.core.selection.individual.MateSelection;
import de.terministic.serein.core.selection.individual.RandomSelection;

public class ParticleIndividual<P, G extends Genome<?>> extends BasicIndividual<P, G> {
	private Individual<P, G> bestAncestor = null;
	

	public ParticleIndividual(G genome, Translator<P, G> translator) {
		super(genome, translator);
	}

	public HashMap<String, Object> getDefaultInheritableProperties() {
		HashMap<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put("Offspring", Populations.<P> emptyPopulation());
		defaultProperties.put("Parents", Populations.<P> emptyPopulation());
	    defaultProperties.put("Recombination", null);
	    defaultProperties.put("Mutation", null);
	    defaultProperties.put("MateSelection", new MateSelection<P>(getGenome().getGenomeId(), new RandomSelection<P>()));
	    defaultProperties.put("GlobalBehaviorWeight", 1.0 / 3.0);
	    defaultProperties.put("LocalBehaviorWeight", 1.0 / 3.0);
	    defaultProperties.put("RandomBehaviorWeight", 1.0 / 3.0);
	    defaultProperties.put("Velocity", 0.05);		
		return defaultProperties;
	}
	
	public Individual<P, G> getOffspring() {
		Population<P> offspringParents = new PopulationImpl<P>();
		offspringParents.add(this);
		G offspringGenome = buildOffspringGenotype();
		Individual<P, G> result = buildOffspring(offspringGenome, offspringParents);
		updateBestAncestor(result);
		return result;
	}
	
	protected void updateBestAncestor(Individual<P, G> result) {
		if (bestAncestor == null) {
			bestAncestor = result;
		} else if (getEvolutionEnvironment().getReferenceFitnessComparator().compare(
				bestAncestor, result) < 0.0) {
			bestAncestor = result;
		}
	}
	
	protected G buildOffspringGenotype() {
		G offspringGenome = getGenome();
		double globalTreshold = this.<Double>getProperty("GlobalBehaviorWeight");
		double localTreshold = globalTreshold + this.<Double>getProperty("LocalBehaviorWeight");
		double p = getRandom().nextDouble();
		if (p < globalTreshold) {
			offspringGenome = moveToGlobalOptimum();
		} else if (p < localTreshold) {
			offspringGenome = moveToLocalOptimum();
		} else {
			offspringGenome = moveRandomly();
		}

		return offspringGenome;
	}

	protected G moveToGlobalOptimum() {
		Population<P> offspringParents = new PopulationImpl<P>();
		offspringParents.add(this);
		offspringParents.add(findOptimalMate());
		return recombine(offspringParents);
	}

	protected G moveToLocalOptimum() {
		Population<P> offspringParents = new PopulationImpl<P>();
		offspringParents.add(this);
		offspringParents.add(getBestAncestor());
		return recombine(offspringParents);
	}

	protected G moveRandomly() {
		return mutate(getGenome());
	}
	
	/**
	 * Sets the weights for the movement rules used by the particle. Weight for
	 * random movement is set implicitly random = 1.0 - global -local
	 * 
	 * @param local
	 * @param global
	 * @throws SereinException
	 */
	public void setBehaviorWeights(double local, double global)
			throws SereinException {
		if (0.0 >= local || 0.0 >= global || local + global >= 1.0)
			throw new SereinException(
					"weights out of bounds: Each weight should be between 0.0 and 1.0 and sum of weights should not exceed 1.0");
		this.<Double>setProperty("GlobalBehaviorWeight", global, true);
		this.<Double>setProperty("LocalBehaviorWeight", local, true );
		this.<Double>setProperty("RandomBehaviorWeight", 1.0 - local - global, true);
	}
	
	public void setVelocity(double velocity) {
		this.<Double>setProperty("Velocity",velocity, true);
	}
	
	/**
	 * Tries to find the globally known optimal solution, that has a compatible
	 * type of Genome. If the current fittest Individual is incompatible it
	 * searches the current population for the fittest compatible individual.
	 * 
	 * @return the fittest individual
	 */
	@SuppressWarnings("unchecked")
	protected Individual<P, G> findOptimalMate() {
		Individual<P, G> result = null;
		try {
			result = (Individual<P, G>) getEvolutionEnvironment().getFittest();
		} catch (Exception e) {
			result = (Individual<P, G>) this.<IndividualSelection<P>>getProperty("MateSelection").selectIndividuals(
					getEvolutionEnvironment().getPopulation()).getFittest(
							getEvolutionEnvironment().getReferenceFitnessComparator());
		}
		return result;
	}
	
	protected Individual<P,G> getBestAncestor() {
		if (bestAncestor == null) {
			return this;
		}
		return bestAncestor;
	}
}
