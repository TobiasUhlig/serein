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
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.EvolutionObject;
import de.terministic.serein.api.Genome;
import de.terministic.serein.api.Individual;
import de.terministic.serein.api.IndividualSelection;
import de.terministic.serein.api.Mutation;
import de.terministic.serein.api.Population;
import de.terministic.serein.api.Recombination;
import de.terministic.serein.api.RecombinationException;
import de.terministic.serein.api.Translator;
import de.terministic.serein.core.selection.individual.MateSelection;
import de.terministic.serein.core.selection.individual.RandomSelection;
import de.terministic.serein.core.util.IdGenerator;

public class BasicIndividual<P, G extends Genome<?>> implements Individual<P, G> {

	private final G 				genome;
	private P 						phenotype;
	private final long 				id;
	private Random 					random;
	private Translator<P, G> 		translator;
	private EvolutionEnvironment<P> referenceEa;
	private HashMap<String, Object> privateProperties;
	private HashMap<String, Object> inheritableProperties;
	protected boolean 				trackAncestors = false;


	
	public BasicIndividual(G genome, Translator<P, G> translator) {
		this.genome = genome;
		this.id = IdGenerator.getUniqueId();
		this.translator = translator;
		this.inheritableProperties = getDefaultInheritableProperties();
		this.privateProperties = new HashMap<>();
		this.setProperty("Offspring", Populations.emptyPopulation(), false);
	}
	
	public HashMap<String, Object> getDefaultInheritableProperties() {
		HashMap<String, Object> defaultProperties = new HashMap<>();
	    defaultProperties.put("Recombination", null);
	    defaultProperties.put("Mutation", null);
	    defaultProperties.put("MateSelection", new MateSelection<P>(genome.getGenomeId(), new RandomSelection<P>()));  
	    defaultProperties.put("ProbabilityOfMutation", 1.0);
	    defaultProperties.put("ProbabilityOfRecombination", 1.0);			
		return defaultProperties;
	}
	
	@Override
	public void setEnvironment(EvolutionEnvironment<P> referenceEa) {
		this.referenceEa = referenceEa;
		if (referenceEa != null) {
			this.random = referenceEa.getRandom();
			this.<EvolutionObject<P>>getProperty("MateSelection").setEnvironment(referenceEa);
		}
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public G getGenome() {
		return genome;
	}

	@Override
	public P getPhenotype() {
		if (this.phenotype == null) {			
			this.phenotype = translate(genome);
		}
		return phenotype;
	}

	@Override
	public Individual<P, G> getRandomOffspring(Random random) {
		Population<P> offspringParents = new PopulationImpl<P>();
		offspringParents.add(this);
		@SuppressWarnings("unchecked")
		G offspringGenome = (G) getGenome().createRandomInstance(random);
		return buildOffspring(offspringGenome, offspringParents);
	}

	@Override
	public Individual<P, G> getOffspring() {
		Population<P> offspringParents = Populations.emptyPopulation();
		G offspringGenome = null;
		
		double p = getRandom().nextDouble();
		
		if (p < this.<Double> getProperty("ProbabilityOfRecombination")) {
			offspringGenome = recombine(offspringParents);
		} 
		
		if (offspringGenome == null){
			offspringParents.add(this);
			offspringGenome = getGenome();
			p = -1.0; //mutation always happens if no recombination was used
		} else {
			p = getRandom().nextDouble();
		}
		
		if (p < this.<Double> getProperty("ProbabilityOfMutation")) {
			offspringGenome = mutate(offspringGenome);
		}
		return buildOffspring(offspringGenome, offspringParents);
	}
	
	
	
	protected Individual<P, G> buildOffspring(G offspringGenome, Population<P> offspringParents) {
		Individual<P, G> offspring = instantiateIndividual(offspringGenome);	
		offspring.setProperties(inheritableProperties, true);
		//offspring.setEnvironment(getEvolutionEnvironment());
		if (trackAncestors) {
			// add a List containing all parents of offspring as property
			offspring.<Population<P>> setProperty("Parents", offspringParents, false);
			// update own List of generated Offspring
			this.<Population<P>> getProperty("Offspring").add(offspring);
			((BasicIndividual<P, G>) offspring).activateAncestorTracking(true);
		}
		return offspring;
	}
	
	protected Individual<P,G> instantiateIndividual(G genome){
		return new BasicIndividual<P, G>(genome, translator);
	}

	protected void addOffspringParents(Population<P> parents) {
		parents.add(this);	
		IndividualSelection<P> mateSelection = this.<IndividualSelection<P>> getProperty("MateSelection");
		mateSelection.setEnvironment(this.getEvolutionEnvironment());	
		@SuppressWarnings("unchecked")
		Individual<P, G> mate = (Individual<P, G>) mateSelection.selectIndividual(getEvolutionEnvironment().getPopulation());
		parents.add(mate);
	}

	@SuppressWarnings("unchecked")
	protected G recombine(Population<P> offspringParents) {		
		G offspringGenome = null;
		if (getProperty("Recombination") != null) {
			addOffspringParents(offspringParents);
			List<G> parentalGenomes = new ArrayList<G>();
			for (Individual<P, ?> parent : offspringParents) {
				parentalGenomes.add((G) parent.getGenome());
			}
			try {
				offspringGenome = this.<Recombination<G>> getProperty("Recombination").recombine(parentalGenomes, getRandom());
			} catch (RecombinationException e) {
				// TODO Log error
			}
		}	
		return offspringGenome;
	}

	protected G mutate(G genome) {
		return this.<Mutation<G>> getProperty("Mutation").mutate(genome, getRandom());
	}

	protected Random getRandom() {
		return random;
	}
	protected Translator<P, G> getTranslator() {
		return translator;
	}
	protected EvolutionEnvironment<P> getEvolutionEnvironment() {
		return referenceEa;
	}

	/**
	 * Use to activate or deactivate tracking of ancestors. If activate the
	 * individuals keep track of their ancestors by storing references to their
	 * parents and offspring. Active ancestor tracking requires a large amount
	 * of memory, since basically all individuals are kept in memory.
	 * 
	 */
	public void activateAncestorTracking(boolean active) {
		this.trackAncestors = active;
	}
	
	protected P translate(G genome) {
		return getTranslator().translate(genome);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getProperty(String key) {
		
		T result = null;
		if (privateProperties.containsKey(key)){
			result = (T) privateProperties.get(key);
		} else {
			result = (T) inheritableProperties.get(key);
			
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T removeProperty(String key) {
		
		T result = null;
		if (privateProperties.containsKey(key)) {
			result = (T) privateProperties.remove(key);
		}
		if (inheritableProperties.containsKey(key)) {
			result = (T) inheritableProperties.remove(key);
		}
		
		return result;
	}

	@Override
	public <T> boolean hasProperty(String key) {
		boolean result = inheritableProperties.containsKey(key) || privateProperties.containsKey(key);
		return result;
	}

	@Override
	public void setProperties(HashMap<String, Object> properties, boolean inheritable) {
		if (inheritable) {
			inheritableProperties.putAll(properties);
		} else {
			privateProperties.putAll(properties);
		}
	}

	@Override
	public List<String> getProperties() {
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll(inheritableProperties.keySet());
		keys.addAll(privateProperties.keySet());
		return keys;
	}
	
	@Override
	public String toString() {
		return getPhenotype().toString();
	}
	
	public void setMutation(Mutation<G> mutation) {
		this.<Mutation<G>>setProperty("Mutation", mutation, true);
	}
	
	public void setRecombination(Recombination<G> recombination) {
		this.<Recombination<G>>setProperty("Recombination", recombination,true);
	}
	
	public void setMateSelection(IndividualSelection<P> mateSelection) {
		mateSelection.setEnvironment(this.getEvolutionEnvironment());
		this.<IndividualSelection<P>>setProperty("MateSelection", mateSelection,true);
	}

	@Override
	public <T> void setProperty(String key, T value, boolean inheritable) {
		if (inheritable) {
			inheritableProperties.put(key, value);
		} else {
			privateProperties.put(key, value);
		}
		
	}
	
	

}
