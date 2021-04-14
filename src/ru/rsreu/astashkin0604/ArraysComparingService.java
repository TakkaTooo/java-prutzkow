package ru.rsreu.astashkin0604;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.astashkin0604.scholarship.ScholarshipSheet;

/**
 * A class that implements methods for performing a task in accordance with a
 * task option.
 * 
 * @author Maxim Astashkin.
 *
 */
public class ArraysComparingService {

	private ArraysComparingService() {

	}

	/**
	 * Compares element-wise two arrays and stores the comparison results in the
	 * array.
	 * 
	 * @param first  - first array.
	 * @param second - second array.
	 * @return comparison results by array.
	 */
	public static int[] compareArraysElements(ScholarshipSheet[] first, ScholarshipSheet[] second) {

		int[] output = new int[Math.min(first.length, second.length)];
		for (int i = 0; i < first.length && i < second.length; i++) {
			output[i] = first[i].compareTo(second[i]);
		}
		return output;
	}

	/**
	 * Form string with resutls of comparing two arrays.
	 * 
	 * @param firstArray  - first array.
	 * @param secondArray - second array.
	 * @return forming result.
	 */
	public static String formCompareResults(ScholarshipSheet[] firstArray, ScholarshipSheet[] secondArray,
			String labelResourceKey) {
		StringBuilder output = new StringBuilder();
		int[] comparative = ArraysComparingService.compareArraysElements(firstArray, secondArray);
		output.append(Resourcer.getString(labelResourceKey))
				.append(ArraysComparingService.parseComparativeArrayToString(comparative));
		return output.toString();
	}

	/**
	 * Converts a comparison array (consisting of -1 0 and 1) to a textual
	 * representation of the comparison of two arrays 1. First equal second (for
	 * example).
	 * 
	 * @param array - comparison array.
	 * @return text representation of arrays comparing.
	 */
	private static String parseComparativeArrayToString(int[] array) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			String comparative = null;
			if (array[i] == 0) {
				comparative = Resourcer.getString("runner.message.equal");
			} else if (array[i] > 0) {
				comparative = Resourcer.getString("runner.message.more");
			} else {
				comparative = Resourcer.getString("runner.message.less");
			}

			output.append(String.format(Resourcer.getString("runner.message.compareFormat"), i + 1, comparative));
		}

		return output.toString();
	}

}
