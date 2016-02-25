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

public class AckleyFunction implements BenchmarkFunction {
	
	public final static Domain DEFAULT_DOMAIN = new Domain(-32.768, 32.768);
	private final Domain domain;
	
	public AckleyFunction() {
		this(DEFAULT_DOMAIN);
	}
	
	public AckleyFunction(Domain domain) {
		this.domain = domain;
	}



	@Override
	public double map(RealVector v) {
		double[] x = v.toArray();
		int n = x.length;
		double sum1 = 0.0, sum2 = 0.0;

		for (int i = 0; i < n; i++){
			sum1 += x[i]*x[i];
			sum2 += Math.cos(2 * Math.PI * x[i]);
		}
		double fx = -20 * Math.exp(-0.2 * Math.sqrt( sum1 / n)) - Math.exp( sum2 / n) + 20 + Math.E;
		return fx;
	}

	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public RealVector getOptimum(int dimension) {
		return new ArrayRealVector(dimension, 0.0);
	}
	
}
