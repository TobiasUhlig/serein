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
package de.termininistic.serein.examples.benchmarks.functions.multimodal;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import de.termininistic.serein.examples.benchmarks.functions.BenchmarkFunction;
import de.termininistic.serein.examples.benchmarks.functions.Domain;



public class LevyFunction implements BenchmarkFunction {
	
	public final static Domain DEFAULT_DOMAIN = new Domain(-10.0, 10.0);

	
	private final Domain domain;

	
	public LevyFunction() {
		this(DEFAULT_DOMAIN);
	}
	
	public LevyFunction(Domain domain) {
		this.domain = domain;
	}



	@Override
	public double map(RealVector v) {
		double[] x = v.toArray();
		double sum = Math.pow(Math.sin(Math.PI * w(x[0])), 2);
		for(int i = 0; i < x.length-1; i++) {
			sum += Math.pow((w(x[i])-1), 2) * 
					(1 + 10 * Math.pow(Math.sin(2 * Math.PI * 
					w(x[0]) + 1), 2) + Math.pow(w(x[x.length-1]) -1, 2) *
					(1 + Math.pow(Math.sin(2 * Math.PI * w(x[x.length-1]) + 1), 2))); 
		}
		return sum;
	}
	
	private double w(double x) {
		return 1 + (x - 1) / 4;
	}
	

	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public RealVector getOptimum(int dimension) {
		return new ArrayRealVector(dimension, 1.0);
	}
	
	public static void main(String[] args) {
		LevyFunction levy = new LevyFunction();
		System.out.println(levy.map(levy.getOptimum(5)));
	}
	
}
