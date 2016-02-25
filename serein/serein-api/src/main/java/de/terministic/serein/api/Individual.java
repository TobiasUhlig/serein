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
package de.terministic.serein.api;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * An individual is the central element of serein. It encapsulates a candidate
 * solution (its phenotype) for a certain problem. The phenotype is derived from
 * the genotype - the internal encoding for a candidate solution. The type of
 * the encoded problem and the way it is encoded are fixed. The most important
 * ability of an individual is to reproduce by generating a single offspring.
 * 
 * @author tobias uhlig
 * 
 * @param <P>
 *            Phenotype - the kind of candidate solution represented by the
 *            individual
 * @param <G>
 *            Genotype - the kind of genetic encoding employed by this
 *            individual
 */
public interface Individual<P, G extends Genome<?>> extends EvolutionObject<P> {
	

	/**
	 * Get the candidate solution (the phenotype) represented by this
	 * individual. The phenotype is expected to be constant, hence it should not
	 * be changed after the individual has been generated. This is necessary to
	 * allow valid caching of fitness values for individuals.
	 * 
	 * @return the phenotype of the individual
	 */
	P getPhenotype();

	/**
	 * Get the encoding of the candidate solution represented by this
	 * individual. This should only be called by other individuals (mates) for
	 * recombination purposes. The genome is expected to be constant, hence it
	 * should not be changed after the individual has been generated. Since the
	 * the phenotype also does not change after the generation of an individual,
	 * any changes to the genome would have no effect.
	 * 
	 * @return the genotype of the individual
	 */
	G getGenome();

	/**
	 * An offspring is generated using various evolutionary operators to derive
	 * it from this individual. An offspring has the same kind of phenotype and
	 * genotype as generating parent.
	 * 
	 * @return an offspring derived from this individual
	 */
	Individual<P, ? extends Genome<?>> getOffspring();

	/**
	 * An offspring is generated using various evolutionary operators to derive
	 * it from this individual. An offspring has the same kind of phenotype and
	 * genotype as generating parent.
	 * 
	 * @param random
	 * 
	 * @return an offspring derived from this individual
	 */
	Individual<P, ? extends Genome<?>> getRandomOffspring(Random random);


	
	void setEnvironment(EvolutionEnvironment<P> referenceEa);
	
	/**
	 * Returns a unique identifier of the indiviudal.
	 * 
	 * @return a unique identifier of the individual
	 */
	Long getId();

	
	
	<T> T getProperty(String key);

	<T> void setProperty(String key, T value, boolean inheritable);
	
	void setProperties(HashMap<String, Object> properties, boolean inheritable);

	List<String> getProperties();

	<T> T removeProperty(String key);

	<T> boolean hasProperty(String key);

}
