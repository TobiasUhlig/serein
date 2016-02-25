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
package de.termininistic.serein.examples.benchmarks.functions.unimodal;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import de.termininistic.serein.examples.benchmarks.functions.BenchmarkFunction;
import de.termininistic.serein.examples.benchmarks.functions.Domain;

public class PermFunction implements BenchmarkFunction {
	public final static Domain DEFAULT_DOMAIN = new Domain(-10, 10);
	public final static double DEFAULT_BETA = 10.0;
	private final Domain domain;
	private double beta;
	
	public PermFunction() {
		this(DEFAULT_BETA, DEFAULT_DOMAIN);
	}
	
	public PermFunction(double beta, Domain domain) {
		this.domain = domain;
		this.beta = beta;
	}

	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public RealVector getOptimum(int dimension) {
		double[] optimum = new double[dimension];
		for(int i = 0; i < dimension; i++) {
			optimum[i] = 1 / (i+1);
		}
		return new ArrayRealVector(optimum);
	}

	@Override
	public double map(RealVector v) {
		double sum = 0.0;
		double[] x = v.toArray();
		for (int i = 0; i < x.length; i++) {
			double innerSum = 0.0;
			for (int j = 0; j < x.length; j++) {
				innerSum += (j+1 + beta) * (Math.pow(x[j], i+1) - 1 / Math.pow(j+1, i+1));
			}
			sum += innerSum * innerSum;
		}
		return sum;
	}

}
