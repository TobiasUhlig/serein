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
package de.termininistic.serein.examples.tsp;

import java.util.ArrayList;
import java.util.List;

public class Tour extends ArrayList<Location>{
	
	private static final long serialVersionUID = 1L;

	public Tour() {
		super();
	}
	
	public Tour(List<Location> locations) {
		super(locations);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Location l : this) {
			sb.append(l.getId()).append(", ");
		}
		sb.append("d = ");
		sb.append(length());
		//return sb.toString();
		return length()+"";
	}
	
	public double length() {
		double result = 0;
		for (int i = 0; i < this.size() - 1; i++) {
			result = result + this.get(i).getDistance(this.get(i + 1));
		}
		result = result + this.get(0).getDistance(this.get(this.size()-1));
		return result;
	}
	
	public double getDiameter() {
		double minX = Double.MAX_VALUE;
		double minY = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		
		for (Location l : this) {
			if (maxX < l.getX()) {
				maxX = l.getX();
			} 
			if (minX > l.getX()) {
				minX = l.getX();
			}	
			if (maxY < l.getY()) {
				maxY = l.getY();
			} 
			if (minY > l.getY()) {
				minY = l.getY();
			}	

		}
		
		double result = Math.pow(minX - maxX, 2) + Math.pow(minY -maxY, 2);
		result = Math.sqrt(result);
		return result;	
	}
}
