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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Provides some convenience methods to manipulate Lists which are missing in
 * the class java.util.Collections.
 * 
 * @author tobias uhlig
 * 
 */
public class Lists {

	/**
	 * Randomly swaps two different elements of a list.
	 * 
	 * @param list
	 *            the list
	 * @param random
	 *            a random number generator
	 */
	public static void randomSwap(List<?> list, Random random) {
		int size = list.size();
		int i = random.nextInt(size);
		int j;
		do {
			j = random.nextInt(size);
		} while (i == j);
		Collections.swap(list, i, j);
	}



	/**
	 * Shifts an element in given list from one position to another.
	 * 
	 * @param list
	 * @param from
	 * @param to
	 */
	public static void shiftElement(List<?> list, int from, int to) {
		if (from < to) {
			Collections.rotate(list.subList(from, to + 1), -1);
		} else {
			Collections.rotate(list.subList(to, from + 1), 1);
		}	
	}


	/**
	 * Test whether a list is a permutation of another list. 
	 * @param list1 the first list
	 * @param list2 the second list
	 * @return true if list1 is a permutation of list2
	 */
	public static <E> boolean isPermutation(List<E> list1, List<E> list2) {
		if (list1 == null || list2 == null) {
			return false;
		}

		if (list1 == list2) {
			return true;
		}

		if (list1.size() != list2.size()) {
			return false;
		}
		if (list1.isEmpty()) {
			return true;
		}

		List<E> temp = new ArrayList<E>(list2);
		for (E e : list1) {
			if (!temp.remove(e)) {
				return false;
			}
		}

		if (temp.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
