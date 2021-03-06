package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public  String acronym(String phrase) {
	
		//Loop to search through string to find whitespace or punctuation without whitespace before or after
		ArrayList<Character> list = new ArrayList<>();
		int i = 0;
		while (i < phrase.length()) {
		
			if (i == 0) {
				list.add(phrase.charAt(i));
			}
			//Note each time Whitespace / punctuation without whitespace is found and Store the chars in a list
			if (phrase.charAt(i) == ' ' || punctuation(i,phrase)) {
				list.add(phrase.charAt(i+1));
			}
			i++;
		}
		StringBuilder builder = new StringBuilder();
		for(Character ch: list) {
			builder.append(ch);
		}
		String output = builder.toString();
		//Capitalize list
		output = output.toUpperCase();
		
		return output;
	}
	public boolean punctuation(int i, String phrase) {
		switch(phrase.charAt(i)) {
		case '-':
		case '+':
		case '.':
		case ':':
		case '_':
			if (phrase.charAt(i+1) != ' ')
				return true;
			break;
		default:
			return false;
		}
		return false;
	}
	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if(sideOne == sideTwo && sideTwo == sideThree) {
				return true;
			}else {
			return false;
			}
		}

		public boolean isIsosceles() {
			if(sideOne == sideTwo ^ sideTwo == sideThree ^ sideThree == sideOne) {
				return true;
			}else {
			return false;
			}
		}

		public boolean isScalene() {
			if(sideOne != sideTwo && sideTwo != sideThree && sideThree != sideOne) {
				return true;
			}else {
			return false;
			}
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
	
		int score = 0;
		for(int i=0;  i< string.length(); i++) {
			switch(string.toLowerCase().charAt(i)) {
				case 'q': 	
				case 'z': score += 10;break;
				case 'j': 
				case 'x': score += 8;break;
				case 'k': score += 5;break;
				case 'y':
				case 'w':
				case 'v':
				case 'h':
				case 'f': score += 4;break;
				case 'p':
				case 'm':
				case 'c':
				case 'b': score += 3;break;
				case 'd':
				case 'g': score += 2;break;
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
				case 'l':
				case 'n':
				case 'r':
				case 's':
				case 't': score +=1;break;
				// left the default as blank to not count punctuation as being worth a point
			}
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		StringBuilder str = new StringBuilder();
		str.append(string);
		// remove (,),-, ,.,+ from str and check if invalid number
		for(int i = 0; i<str.length(); i++) {
			switch(str.charAt(i)){
				case '(':
				case ')':
				case ',':
				case ' ':
				case '.':
				case '-':
				case '+': str.deleteCharAt(i);i--;break;
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '0': break;
				default:
					throw new IllegalArgumentException("INVALID PHONE NUMBER: Only use numbers and and appropriate symbols please.");
			}
		}
		// If stringBuilder[0] == 1, remove 1
		if (str.charAt(0)==1) {
			str.deleteCharAt(0);
		// if stringBuilder.length() != 10
		//		Throw Illegal Argument Exception
		}if (str.length() != 10) {
			throw new IllegalArgumentException("INVALID PHONE NUMBER: This code only processes 10 or 11 digit, North American phone numbers.");
		}
		
		return str.toString();
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		//check out String.split();
		//Always check out the methods available for a class if you are stuck
		//RegEx is love, RegEx is life.
	
		String[] strings = string.split("[ ,\n]{1,2}");
		Map<String, Integer> stringList = new HashMap<String,Integer>();
		for(int i = 0; i < strings.length; i++) {
			if(stringList.get(strings[i]) == null) {
				stringList.put(strings[i], 1);
				
			}else{
				stringList.put(strings[i], stringList.get(strings[i])+1);
			}
		}	
		
		return stringList;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;
		
		public int indexOf(T t) {
			
			int newT = Integer.parseInt(t.toString());
			return binarySearch(newT, 0, sortedList.size()-1);
		}
		
		public int binarySearch(int t, int left, int right) {
			if (sortedList.size() >= 1) {
				
				int mid = left + (right-1)/2; 	// Middle Formula
				//if the element is at the middle return middle element
				
				if (Integer.parseInt(sortedList.get(mid).toString()) == t) {
					
					return mid;
					
				}
				//if the element is smaller than an object in the middle, search lower half
				if (t < Integer.parseInt(sortedList.get(mid).toString())) {
					return binarySearch(t, left, mid-1);
				}else {
				//if the element is larger than the object in the middle, search upper half
				
					return binarySearch(t,mid-1,right);
				}
			}
			return 0;
		
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		// split array on spaces
		String[] strings = new String[1]; 
		if(string.contains(" ")) {
			strings = string.split(" ");
		}else {
			strings[0] = string;
		}
		
		// If word begin with consonant
		StringBuilder sB = new StringBuilder();
		int length = 0;
		for(int i = 0; i<strings.length; i++) {
			
			if(strings[i].startsWith("y")||strings[i].startsWith("r")||strings[i].startsWith("f")) {
				sB.append(strings[i]);
				sB.append(strings[i].charAt(0));
				sB.deleteCharAt(length);
			}else
			if (strings[i].startsWith("th")||strings[i].startsWith("qu")) {
//				put consonant at the end
				sB.append(strings[i]);
				sB.append(strings[i].charAt(0));
				sB.append(strings[i].charAt(1));
				sB.deleteCharAt(0);
				sB.deleteCharAt(0);
			}else 
			if(strings[i].startsWith("sch")){
				sB.append(strings[i]);
				sB.append(strings[i].charAt(0));
				sB.append(strings[i].charAt(1));
				sB.append(strings[i].charAt(2));
				sB.deleteCharAt(0);
				sB.deleteCharAt(0);
				sB.deleteCharAt(0);
			}else {
				sB.append(strings[i]);
			}
			// Put "ay" on the end
			sB.append("ay");
			if(strings.length > 1) {
				if(length < 15)
				sB.append(" ");
			}
			length = sB.length();
		// AND
		}
		return sB.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		int c = 0,a,temp;
		temp = input;
		int noOfDigits = String.valueOf(input).length();
		if (input > 9 ) { 
			while (input > 0) {
			
				a=input%10;
				int aToThePowerOfNoOfDigits = 1;
				input /= 10;
				for(int i = 0; i<noOfDigits; i++) {
					aToThePowerOfNoOfDigits *= a;
				}
				c += aToThePowerOfNoOfDigits;
			
			}
		}else {
			c = input;
		}
		return temp == c;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// give it time, she works eventually. like, 675,941 iterations.
		
		
		long prime = 2L;
		long iteratedFactor = l;
		List<Long> primeFactors = new ArrayList<Long>();
		long pFi = 0;
		while(pFi<l/2) {
			if (iteratedFactor%prime == 0) {
				iteratedFactor /= prime;
				
				primeFactors.add(prime);		
			}	
			for (int i = 0; i<primeFactors.size(); i++) {
				if (iteratedFactor%primeFactors.get(i) == 0) {
					iteratedFactor /= primeFactors.get(i);
					
					primeFactors.add(prime);
				}
			}	
			if (prime < l/2) {
				prime = nextPrime(prime, l);
			}
			
			pFi++;
		}
		return primeFactors;
	}

	private long nextPrime(long prime, long l) {
		boolean flag = false;
		int flags = 0;
		long num = prime;
		
		do {
			num++;
			for(long i = 2; i<= num; i++) {
				if(num % i == 0) {
					flags++;						
					break;
				}if (flags == 0) {
					flag = true;
				}
			}	
			flags = 0;
			System.out.println(num);
		}while(!flag);
		
		return num;
	}
	
	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			String plaintext = "abcdefghijklmnopqrstuvwxyz";
			String PLAINTEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String plainTEXT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
			
			StringBuilder cypherWIP = new StringBuilder();
			StringBuilder CYPHER = new StringBuilder();
			StringBuilder sB = new StringBuilder();
			//build cypher
			for (int i = 0; i<26; i++) {
				if(key+i<plaintext.length()) {
					cypherWIP.append(plaintext.charAt(key+i));
				}else {
					cypherWIP.append(plaintext.charAt((key+i)-26));
				}
			}
			for (int i = 0; i<26; i++) {
				if(key+i<PLAINTEXT.length()) {
					CYPHER.append(PLAINTEXT.charAt(key+i));
				}else {
					CYPHER.append(PLAINTEXT.charAt((key+i)-26));
				}
			}
			cypherWIP.append(CYPHER.toString());
		
			String cypher = cypherWIP.toString();
		
			for(int i=0; i<string.length(); i++) {
				if(string.charAt(i)!= '1' && string.charAt(i)!= '2' && string.charAt(i)!= '3' && string.charAt(i)!= '4' && string.charAt(i)!= '5' && string.charAt(i)!= '6' && string.charAt(i)!= '7' && string.charAt(i)!= '8' && string.charAt(i)!= '9' && string.charAt(i)!=  '0' && string.charAt(i)!= '\'' && string.charAt(i)!= '.'&& string.charAt(i)!= '!' && string.charAt(i)!= ','&& string.charAt(i)!= ' ') {
					sB.append(cypher.charAt(plainTEXT.indexOf(string.charAt(i))));
				}else {
					sB.append(string.charAt(i));
				}
			}
			return sB.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		long nthPrime = 2;
		if (i < 1) {
			throw new IllegalArgumentException();
			
		}
		if(i == 1) {
			return (int)nthPrime;
		}
		
		for (int j = 1; j < i; j++) {
			nthPrime = nextPrime(nthPrime);
		
		}
		return (int)nthPrime;
	}
	private long nextPrime(long prime) {
		boolean flag = false;
		int flags = 0;
		long num = prime;
		
		do {
			
			num++;
		
			for(long i = 2; i<= num; i++) {
				
				if(num % i == 0) {
				
					flags++;
					continue;
					
					
				}if (flags == 0) {
					flag = true;
				}else {
					flag = false;
				}
			}	
			flags = 0;
			
		}while(!flag);
		
		return num;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			string = string.toLowerCase();
			String plaintext = "abcdefghijklmnopqrstuvwxyz";
			String cypher =	   "zyxwvutsrqponmlkjihgfedcba";
			StringBuilder sB = new StringBuilder();
			int stringIndex = 0;
			for(int i=0; i<string.length(); i++) {
				if(stringIndex != 0 && string.charAt(i) != '.') 
					{if (stringIndex%5 == 0) {
						if (sB.charAt(sB.length()-1)!= ' ') {
							sB.append(" ");
						}
					}
				}
				
				if(string.charAt(i)==',' || string.charAt(i)== ' ' || string.charAt(i)== '.'){
					continue;
				}
				if(string.charAt(i)!= '1' && string.charAt(i)!= '2' && string.charAt(i)!= '3' && string.charAt(i)!= '4' && string.charAt(i)!= '5' && string.charAt(i)!= '6' && string.charAt(i)!= '7' && string.charAt(i)!= '8' && string.charAt(i)!= '9' && string.charAt(i)!=  '0') {
					sB.append(cypher.charAt(plaintext.indexOf(string.charAt(i))));
					
				}else{
					sB.append(string.charAt(i));
				}
				stringIndex++;
			}
			return sB.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			String plaintext = "abcdefghijklmnopqrstuvwxyz1234567890";
			String cypher =	   "zyxwvutsrqponmlkjihgfedcba1234567890";
			StringBuilder sB = new StringBuilder();
			for( int i = 0; i<string.length(); i++) {
				if( string.charAt(i) != ' ') {
					sB.append(plaintext.charAt(cypher.indexOf(string.charAt(i))));
				}
			}
			return sB.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		
		
		boolean valid = false;
		//sanitize string
		StringBuilder sB = new StringBuilder();
		for(int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != '-') {
				sB.append(string.charAt(i));
			}
		}
		String isbn = sB.toString();
		int lastDigit = 0;
		// check if last char is 0-9 or X
		Pattern r = Pattern.compile("[0-9X]");
		// check if body is only digits
		Pattern s = Pattern.compile("[^0-9]");
		sB.deleteCharAt(isbn.length()-1);
		String digitsOnly = sB.toString();
		String lastIndex = Character.toString(isbn.charAt(isbn.length()-1));
		Matcher n = s.matcher(digitsOnly);
		Matcher m = r.matcher(lastIndex);
		if(n.find()) {
			return false;
		}else {
		if (m.find()) {
			if (lastIndex.charAt(0) == 'X') {
				lastDigit = 10;
			}else {
				lastDigit = Integer.parseInt(lastIndex);
			}
			int store = 0;
			for(int i = 0, j=isbn.length(); i<(isbn.length()-1); i++, j--) {
				
				store += (Integer.parseInt(Character.toString(isbn.charAt(i)))* j);
			}
			store += lastDigit;
			if (store % 11 == 0) {
				return true;
			}
		}}
		return valid;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		boolean[] mark = new boolean[26];
		int index = 0;
		string = string.toLowerCase();
		for( int i = 0; i<string.length(); i++) {
			if ('a' <= string.charAt(i) && string.charAt(i) <= 'z') {
				index = string.charAt(i) - 'a';
			}else {
				continue;
			}
			//System.out.println(index);
			mark[index] = true;
		}
		for(int i = 0; i<26; i++) {
			if(mark[i] == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
	
		LocalDateTime l;
		if(given instanceof LocalDate ) {
			l = ((LocalDate) given).atStartOfDay();
		}else {
			l = (LocalDateTime)given;
		}
		l = l.plusSeconds(1000000000L);
		return l;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
	
		Set<Integer> multiples = new HashSet<Integer>();
		int sumOfM = 0;
		int multiple = 0;
		for (int j = 0; j<set.length; j++)
			for (int k = 1; k*set[j]<i; k++) {
				multiple = k*set[j];
				//System.out.println(multiple);
				multiples.add(multiple);
				
			}
		Object[] m  = multiples.toArray();
		for (int j = 0; j<multiples.size(); j++)
			sumOfM += (Integer) m[j]; 
		return sumOfM;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		
		// iterate through string to throw into int array
		Integer sum = 0;
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ArrayList<Integer> ints2 = new ArrayList<Integer>();
		ArrayList<Integer> ints3 = new ArrayList<Integer>();
		ArrayList<Integer> ints4 = new ArrayList<Integer>();
		if(string.contains("a")|| string.contains("-")) {
			return false;
		}
		for (int i = 0; i<string.length(); i++) {
			if(string.charAt(i) != ' ') {
				ints.add(Integer.parseInt(Character.toString(string.charAt(i))));
			}
		}
		// work through int array to find every second digit, starting from the right
					//add to array
		for (int i = 1; i<=ints.size(); i++) {
			if(i%2==0) {
				ints2.add(ints.get(i-1));
			}else {
				ints3.add(ints.get(i-1));
			}
		}
		// double each value
		//if doubled value > 9, subtract 9
		for(int i = 0; i<ints2.size(); i++) {
			if(ints2.get(i)*2 > 9) {
				ints4.add(ints2.get(i)*2-9);
			}else {
				ints4.add(ints2.get(i)*2);
			}
			
		}
		//System.out.println(ints4);
		// sum all values
		for(int i = 0; i<ints4.size(); i++) {
			ints3.add(ints4.get(i));
		}
		for(int i = 0; i<ints3.size(); i++) {
			sum += ints3.get(i);
		}
		
		if(sum%10 == 0) {
			return true;
		}else {
			return false;
		}
				
				// if mod sum by 10 = 0
					//return true
				// else
					//return false
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(string);
		sb.deleteCharAt(sb.length()-1);
		string = sb.toString();
		Scanner s = new Scanner(string);
		int count = 0 ,i=0;
		int[] num = new int[2];
		while (s.hasNext()) {
			//System.out.println(s.hasNextInt());
			if(s.hasNextInt() && count<3) {
				count++;
				num[i] = s.nextInt();
				//System.out.println(num[i]);
				i++;
			}else {
				s.next();
			}
		}
		s.close();
		if(string.contains("plus")) {
			return (num[0]+num[1]);
		}
		if(string.contains("minus")) {
			return num[0]-num[1];
		}
		if(string.contains("multiplied")) {
			return num[0]*num[1];
		}
		if(string.contains("divided")) {
			return num[0]/num[1];
		}
		return 0;
	}

}
