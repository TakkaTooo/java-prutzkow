package ru.rsreu.astashkin0404;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ArrayUtils() {

	}

	/**
	 * Returns the indices of the elements of an array by value.
	 * 
	 * @param values - input array.
	 * @param value  - value for searchin indeces.
	 * @return List of indeces from values array.
	 */
	public static List<Integer> getIndexesByValue(int[] values, int value) {
		List<Integer> indeces = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			if (values[i] == value) {
				indeces.add(i);
			}
		}
		return indeces;
	}

	/**
	 * @param values - input array.
	 * @return max value in values array.
	 */
	public static int getArrayMaxValue(int[] values) {
		int maxValue = values[0];
		for (int item : values) {
			if (item > maxValue) {
				maxValue = item;
			}
		}
		return maxValue;
	}

	/**
	 * Converts List to array.
	 * 
	 * @param list - input List.
	 * @return int[] from List.
	 */
	public static int[] listToArray(List<Integer> list) {
		int[] items = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			items[i] = list.get(i);
		}
		return items;
	}
}
