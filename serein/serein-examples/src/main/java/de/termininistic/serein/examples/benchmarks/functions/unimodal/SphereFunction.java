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

public class SphereFunction implements BenchmarkFunction {
	public final static Domain DEFAULT_DOMAIN = new Domain(-5.12, 5.12);
	private final Domain domain;
	
	public SphereFunction() {
		this(DEFAULT_DOMAIN);
	}
	
	public SphereFunction(Domain domain) {
		this.domain = domain;
	}

	@Override
	public Domain getDomain() {
		return domain;
	}

	@Override
	public RealVector getOptimum(int dimension) {
		return new ArrayRealVector(dimension, 0);
	}

	@Override
	public double map(RealVector v) {
		double result = 0.0;
		for (double d:v.toArray()) {
			result = result + d*d;
		}
		
		return result;
	}

}
