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
package de.terministic.serein.core.genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;


public final class DoubleGenome extends ValueGenome<Double> {
	private double[] geneArray;
	private RealVector geneVector;
	
	public final static double LOWER_BOUND = 0.0;
	public final static double UPPER_BOUND = 1.0;
	

	public DoubleGenome(List<Double> genes) {
		super(genes);
		if (!inBounds(genes)){
			throw new OutOfBoundsException(LOWER_BOUND, UPPER_BOUND); 
		}
		
	}
	
	@Override
	public String getGenomeId() {
		return this.getClass().getName();
	}



	@Override
	public DoubleGenome createInstance(List<Double> genes) {
		return new DoubleGenome(genes);
	}

	public DoubleGenome createRandomInstance(int size, Random random) {
		List<Double> genes = new ArrayList<>();
		for (int i = 0; i < size; i++) {		
			genes.add(this.getRandomValue(random));	
		}
		return createInstance(genes);
	}
	
	@Override
	public DoubleGenome createRandomInstance(Random random) {
		return createRandomInstance(this.size(), random);
	}

	@Override
	public Double getRandomValue(Random random) {
		return random.nextDouble();
	}
	
	public double[] getGeneArray(){
		if (geneArray == null) {
			geneArray = new double[this.size()];
			List<Double> genes = this.getGenes();
			for (int i = 0; i < this.size(); i++ ) {
				geneArray[i] = genes.get(i);
			}
		}
		return geneArray;
	}
	
	public RealVector getGeneVector() {
		if (geneVector == null) {
			geneVector = new ArrayRealVector(getGeneArray());
		}
		return geneVector; 
	}
	
	static public DoubleGenome createRandomDoubleGenome(int size, Random random) {
		List<Double> genes = new ArrayList<>();
		for (int i = 0; i < size; i++) {		
			genes.add(random.nextDouble());	
		}
		return new DoubleGenome(genes);
	}
	public static boolean inBounds(List<Double> genes) {
		 for(double d : genes) {
			 if (LOWER_BOUND > d || d > UPPER_BOUND) return false;
		 }
		 return true;
	}
	
	public static void limitToBounds(List<Double> genes) {
		 for(int i = 0; i < genes.size(); i++) {
			 
			 if (genes.get(i) < LOWER_BOUND) {
				 genes.set(i, LOWER_BOUND);
			 } else if (genes.get(i) > UPPER_BOUND) {
				 genes.set(i, UPPER_BOUND);
			 }
		 }
	}
}
