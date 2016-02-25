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
package de.terministic.serein.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.math3.stat.StatUtils;

import de.terministic.serein.api.EvolutionEnvironment;
import de.terministic.serein.api.EvolutionListener;
import de.terministic.serein.api.FitnessFunction;

public class StatsListener<P> implements EvolutionListener<P> {
	
	private FitnessFunction<P> fitness;
	private int samplingRate;
	private int counter;
	private List<double[]> stats;
	private DecimalFormat f = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
	
	public StatsListener(FitnessFunction<P> fitness, int samplingRate) {
		this.fitness = fitness;
		this.samplingRate = samplingRate;
		counter = 0;
		stats = new ArrayList<double[]>();
		f.applyPattern("#,##0.######");
		
	}
	
	@Override
	public void update(EvolutionListener.Msg msg, EvolutionEnvironment<P> algo) {
		if (counter % samplingRate == 0 || msg.equals(Msg.FinishedEvolution)){
			
			int size = algo.getPopulation().size();
			double[] fitnessValues = new double[size];
			for(int i = 0; i < size; i++) {
				fitnessValues[i] = fitness.getFitness(algo.getPopulation().get(i));
			}
			double[] statLine = new double[4];
			statLine[0] = counter;
			statLine[1] = StatUtils.min(fitnessValues);
			statLine[2] = StatUtils.mean(fitnessValues);
			statLine[3] = StatUtils.max(fitnessValues);
			stats.add(statLine);
			if (counter == 0) {
				System.out.println("Generation \tFitness_min \tFitness_mean \tFitness_max");
			}
			System.out.println(ArrayToString(statLine) + "	"+algo.getFittest().toString());
		}
		counter++;	
	}
	
	private String ArrayToString(double[] array) {
		StringBuilder result = new StringBuilder();	
		for (double d:array) {
			StringBuilder str = new StringBuilder("        ");
			str.append(f.format(d));
			int i = str.indexOf(".");
			if (i > 0) {
				str.append("0000");
				result.append(str.substring(i-8,i+7));
			} else {
				result.append(str.substring(str.length()-9));
			}
			result.append("\t");
		}
		return result.toString();
	}
	
	

}
