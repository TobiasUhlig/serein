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

public class DixonPriceFunction implements BenchmarkFunction {
	public final static Domain DEFAULT_DOMAIN = new Domain(-10, 10);
	private final Domain domain;
	
	public DixonPriceFunction() {
		this(DEFAULT_DOMAIN);
	}
	
	public DixonPriceFunction(Domain domain) {
		this.domain = domain;
	}

	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public RealVector getOptimum(int dimension) {
		double[] optimum = new double[dimension];
		for (int i = 0; i < optimum.length; i++) {
			optimum[i] = Math.pow(2, -((Math.pow(2,i)-2 /Math.pow(2,i))));
		}
		return new ArrayRealVector(optimum);
	}

	@Override
	public double map(RealVector v) {
		
		double[] x = v.toArray();
		int n = x.length;
		double fx = (x[0] - 1) * (x[0] - 1);
		for (int i = 1; i < n; i++) {
			fx += i * Math.pow((2 * x[i] * x[i] - x[i-1]), 2);
		}
		return fx;
	}

}
