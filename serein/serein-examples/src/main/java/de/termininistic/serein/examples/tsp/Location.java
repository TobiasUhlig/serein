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

public class Location {
	private final String id;
	private final double x;
	private final double y;



	public Location(String id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}




	public double getDistance(Location l) {
		double result = 0;
		result = Math.pow(x - l.getX(), 2) + Math.pow(y - l.getY(), 2);
		result = Math.sqrt(result);
		return result;
	}



	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}




	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getId() + "(" + getX() + ", " + getY() + ")";
	}
}
