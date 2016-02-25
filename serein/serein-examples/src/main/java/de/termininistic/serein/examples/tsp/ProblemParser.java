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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;


public class ProblemParser {
	
	
	private ProblemParser() {
		
		
	}
	/**
	 * Parses a tsplib file. This method doesn't handle any errors and parses files for symmetric 2D TSPs.
	 * @param pathname
	 * @return the parsed Tour
	 */
	public static Tour TourFromFile(String pathname) {
		Tour result = new Tour();
        Scanner s = null;	
		try {
			GZIPInputStream gzi = new GZIPInputStream(new FileInputStream(pathname));
			s = new Scanner(gzi);
			//ignore lines until "NODE_COORD_SECTION" begins
			while (s.hasNextLine() && !s.nextLine().contains("NODE_COORD_SECTION"));
			while (s.hasNextLine() ) {
				String line = s.nextLine();
				if (line.contains("EOF")) break;
				Scanner ls = new Scanner(line);
				String name = ls.next();
				double x = Double.valueOf(ls.next());
				double y = Double.valueOf(ls.next());
				result.add(new Location(name, x, y));
				ls.close();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			s.close();
		}
		return result;
	}
}
