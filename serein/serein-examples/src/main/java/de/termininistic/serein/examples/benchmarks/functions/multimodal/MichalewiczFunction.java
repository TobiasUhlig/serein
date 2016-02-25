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

public class MichalewiczFunction implements BenchmarkFunction {
	
	public final static Domain DEFAULT_DOMAIN = new Domain(0.0, Math.PI);

	public  final static int DEFAULT_M_PARAMETER = 10;
	
	private final Domain domain;
	private final int m;
	
	public MichalewiczFunction() {
		this(DEFAULT_DOMAIN, DEFAULT_M_PARAMETER);
	}
	
	public MichalewiczFunction(Domain domain, int m) {
		this.domain = domain;
		this.m = m;
	}



	@Override
	public double map(RealVector v) {
		double[] x = v.toArray();
		double sum = 0.0;
		for(int i = 0; i < x.length; i++) {
			sum += Math.sin(x[i]) * Math.pow(Math.sin((i+1)*x[i]*x[i]/Math.PI), 2*m);
		}
		return -sum;
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
