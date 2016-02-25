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
package de.terministic.serein.core.genome.translators;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import de.terministic.serein.api.Translator;
import de.terministic.serein.core.genome.DoubleGenome;

public class LinearTranslator implements Translator<RealVector, DoubleGenome> {
	
	private RealVector origin;
	private RealVector distance;
	
	
	public LinearTranslator(double dimension, int problemOrder) {
		this(new ArrayRealVector(problemOrder, -dimension), new ArrayRealVector(problemOrder, dimension));
	}
	
	public LinearTranslator(RealVector pointMin, RealVector pointMax) {
		origin = pointMin;
		distance = pointMax.subtract(pointMin);
	}
	
	@Override
	public RealVector translate(DoubleGenome doubleGenome) {
		ArrayRealVector input = new ArrayRealVector(doubleGenome.getGeneVector());
		ArrayRealVector result = input.ebeMultiply(distance).add(origin);
		return result;
	}

}
