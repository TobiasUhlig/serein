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
package de.terministic.serein.core.genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class StringGenome extends ValueGenome<Character> {

	/** A String containing the digits 0 and 1 **/
	public final static String BINARY = "01";
	/** A String containing all digits 0...9 **/
	public final static String DIGITS = "0123456789";
	/** A String containing all capital Letters A...Z **/
	public final static String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/** A String containing all small letters a...z **/
	public final static String SMALL_LETTERS = CAPITAL_LETTERS
			.toLowerCase(Locale.ENGLISH);
	/** A String containing all letters a...zA...Z **/
	public final static String LETTERS = SMALL_LETTERS + CAPITAL_LETTERS;

	private final String alphabet;
	private String geneString;
	private final String genomeId;

	/**
	 * Creates a new string genome.
	 * 
	 * @param genes
	 *            a String containing the genetic information.
	 * @param alphabet
	 *            a String containing all valid characters for this genetic
	 *            code.
	 */
	public StringGenome(List<Character> genes, String alphabet) {
		super(genes);
		this.alphabet = alphabet;
		this.genomeId = "StringGenome-" + alphabet;
	}

	public StringGenome(String genes, String alphabet) {
		this(stringToCharacterList(genes), alphabet);
		this.geneString = genes;
	}

	@Override
	public String getGenomeId() {
		return genomeId;
	}

	public String getAlphabet() {
		return this.alphabet;
	}
	
	public String getGeneString() {
		if (geneString == null) {
			geneString = getStringRepresentation(getGenes());
		}
		return geneString;	
	}

	@Override
	public StringGenome createInstance(List<Character> genes) {
		return new StringGenome(genes, alphabet);
	}

	@Override
	public StringGenome createRandomInstance(Random random) {
		List<Character> genes = new ArrayList<>();
		for (int i = 0; i < this.size(); i++) {
			genes.add(getRandomValue(random));
		}
		return new StringGenome(genes, alphabet);
	}

	@Override
	public Character getRandomValue(Random random) {
		int l = alphabet.length();
		return alphabet.charAt(random.nextInt(l));
	}

	public static List<Character> stringToCharacterList(String str) {
		List<Character> chars = new ArrayList<>();
		for (Character c : str.toCharArray()) {
			chars.add(c);
		}
		return chars;
	}
	
	public static String getStringRepresentation(List<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}

}
