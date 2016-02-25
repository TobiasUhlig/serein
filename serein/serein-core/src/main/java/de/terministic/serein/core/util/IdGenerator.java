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
package de.terministic.serein.core.util;

public class IdGenerator {
	private static long counter = 0;
	
	private IdGenerator(){
		//not instantiable
	}
	
	/**
	 * Generates a unique ID. Currently implemented by counting up and returning the next long value.
	 * 
	 * @return a unique ID
	 */
	public static long getUniqueId(){
		long result = counter;
		counter++;
		return result;
	}
}
