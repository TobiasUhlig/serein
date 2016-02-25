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
package de.termininistic.serein.examples.benchmarks.functions;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Domain {
	public final double max;
	public final double min;
	
	/**
	 * Defines the search domain for test functions
	 * @param min defines the lower bound v_min = (min, min, ...)
	 * @param max defines the upper bound v_max = (max, max, ...)
	 */
	public Domain(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public RealVector getMinVector(int dimension) {
		return new ArrayRealVector(dimension, min);
	}
	
	public RealVector getMaxVector(int dimension) {
		return new ArrayRealVector(dimension, max);
	}
	
	
}
