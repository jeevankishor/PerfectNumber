package com.raremile.perfectnumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to find perfect numbers between a given range
 * 
 * @author JK
 * 
 */
public class FindPerfect {
	public static void main(String[] args) {
		// Note begin time
		long beginTime = System.nanoTime();
		FindPerfect findPerfect = new FindPerfect();

		// List to save perfect numbers returned from the method
		List<Long> numberList = findPerfect.findPerfectNumber(1, 300000000);
		Set<Long> numberSet = new HashSet<Long>();

		// Use a set to eliminate duplicates
		numberSet.addAll(numberList);
		numberList.clear();

		// Add back the elements of the set to the list
		numberList.addAll(numberSet);

		// Sort the elements of the list
		Collections.sort(numberList);
		for (Long number : numberList) {
			System.out.println(number);
		}

		// Note end time
		long stopTime = System.nanoTime();

		// Print the computing time
		System.out.println("Execution time: " + (stopTime - beginTime)
				/ 1000000);
	}

	/**
	 * Method to find perfect number
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */

	public List<Long> findPerfectNumber(long begin, long end) {
		List<Long> perfectList = new ArrayList<Long>();
		for (int i = 2; i < new String("" + ((end < 100) ? 100 : end)).length(); i++) {
			List<Long> numberList = figureNumbers(i);
			for (Long number : numberList) {
				if (isPerfect(number * number) && (number * number) >= begin
						&& (number * number) <= end) {
					perfectList.add(number * number);
				}
			}
		}
		return perfectList;
	}

	/**
	 * Method to find if the number is perfect
	 * 
	 * @param number
	 * @return
	 */
	public boolean isPerfect(long number) {
		double sqrt = Math.sqrt(number);
		if (Math.floor(sqrt) != sqrt) {
			return false;
		}

		if (isPalindrome(number) && isPalindrome((long) sqrt)) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check whether a number is a palindrome
	 * 
	 * @param number
	 * @return
	 */
	public boolean isPalindrome(long number) {
		String strNum = "" + number;
		for (int i = 0; i < strNum.length() / 2; i++) {
			if (strNum.charAt(i) != strNum.charAt(strNum.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to find combinations of 0, 1 and 2
	 * 
	 * @param input
	 * @param level
	 * @param output
	 * @param numberList
	 */
	public void getCombinations(String input, int level, StringBuffer output,
			List<String> numberList) {
		if (level == 0) {
			numberList.add(output.toString());
		} else {
			for (int i = 0; i < input.length(); i++) {
				output.append(input.charAt(i));
				getCombinations(input, level - 1, output, numberList);
				output.deleteCharAt(output.length() - 1);
			}
		}
	}

	/**
	 * Method to convert string to long and add it to the list
	 * 
	 * @param length
	 * @return
	 */
	public List<Long> figureNumbers(int length) {
		List<Long> numberList = new ArrayList<Long>();
		List<String> numStrings = new ArrayList<String>();
		getCombinations("012", length / 2, new StringBuffer(), numStrings);
		numStrings.add("3");
		for (String numString : numStrings) {
			if (numString.startsWith("0")) {
				continue;
			}
			numberList.add(Long.parseLong(numString + mirrorString(numString)));
			numberList.add(Long.parseLong(numString.substring(0,
					numString.length() - 1)
					+ mirrorString(numString)));
		}

		return numberList;
	}

	/**
	 * Method to mirror a string
	 * 
	 * @param string
	 * @return
	 */
	public String mirrorString(String string) {
		String mirrorStr = "";
		for (int i = string.length() - 1; i >= 0; i--) {
			mirrorStr += string.charAt(i);
		}
		return mirrorStr;
	}
}
