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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.EvolutionListener;
import de.terministic.serein.api.FitnessComparator;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.SurvivorSelection;
import de.terministic.serein.api.TerminationCondition;

public abstract class AbstractEvolutionEnvironment<P> implements EvolutionEnvironment<P> {
	private SurvivorSelection<P> survivalSelector;
	private IndividualSelection<P> reproductionSelector;
	private TerminationCondition<P> terminationCondition;
	private Population<P> population;
	private Random random;
	private List<EvolutionListener<P>> listeners = new ArrayList<EvolutionListener<P>>();;
	private FitnessComparator<P> referenceFitnessComparator = null;
	private Individual<P, ?> fittestIndividual = null;

	public AbstractEvolutionEnvironment() {
		setRandom(new Random());
		population = new PopulationImpl<P>();
	}

	@Override
	public Population<P> getPopulation() {
		if (population == null) {
			population = new PopulationImpl<P>();
		}
		return population;
	}

	@Override
	public Random getRandom() {
		return random;
	}

	@Override
	public void setRandom(Random rng) {
		this.random = rng;
	}

	/**
	 * @param terminationCondition
	 *            the terminationCondition to set
	 */
	public void setTerminationCondition(TerminationCondition<P> terminationCondition) {
		this.terminationCondition = terminationCondition;
		this.terminationCondition.setEnvironment(this);
	}

	/**
	 * @return the terminationCondition
	 */
	public TerminationCondition<P> getTerminationCondition() {
		return terminationCondition;
	}

	/**
	 * @param reproductionSelector
	 *            the reproductionSelector to set
	 */
	public void setReproductionSelector(IndividualSelection<P> reproductionSelector) {
		this.reproductionSelector = reproductionSelector;
		this.reproductionSelector.setEnvironment(this);
	}

	/**
	 * @return the reproductionSelector
	 */
	public IndividualSelection<P> getReproductionSelector() {
		return reproductionSelector;
	}

	/**
	 * @param survivalSelector
	 *            the survivalSelector to set
	 */
	public void setSurvivalSelector(SurvivorSelection<P> survivalSelector) {
		this.survivalSelector = survivalSelector;
		this.survivalSelector.setEnvironment(this);
	}

	/**
	 * @return the survivalSelector
	 */
	public SurvivorSelection<P> getSurvivalSelector() {
		return survivalSelector;
	}

	protected void setPopulation(Population<P> population) {
		this.population = population;
		for (Individual<P, ?> i : population) {
			i.setEnvironment(this);
		}
		if (referenceFitnessComparator == null) {
			return;
		}
		// update fittest known individual
		Individual<P, ?> fittestNewIndividual = population.getFittest(referenceFitnessComparator);
		if (fittestIndividual == null) {
			fittestIndividual = fittestNewIndividual;
		} else if (0 > referenceFitnessComparator.compare(fittestIndividual, fittestNewIndividual)) {
			fittestIndividual = fittestNewIndividual;
		}

	}

	@Override
	public void setStartPopulation(Population<P> population) {
		setPopulation(population);

	}

	@Override
	public Individual<P, ?> getFittest() {
		return fittestIndividual;
	}

	public FitnessComparator<P> getReferenceFitnessComparator() {
		return referenceFitnessComparator;
	}

	public void setReferenceFitnessComparator(FitnessComparator<P> referenceFitnessComparator) {
		this.referenceFitnessComparator = referenceFitnessComparator;
	}

	public void addListener(EvolutionListener<P> listener) {
		listeners.add(listener);
	}

	public void removeListener(EvolutionListener<P> listener) {
		listeners.remove(listener);
	}

	protected void update(EvolutionListener.Msg msg) {
		for (EvolutionListener<P> l : listeners) {
			l.update(msg, this);
		}
	}

}
