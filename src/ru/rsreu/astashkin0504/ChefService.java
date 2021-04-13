package ru.rsreu.astashkin0504;

import java.util.Arrays;

import ru.rsreu.astashkin0504.vegetable.*;

public class ChefService {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ChefService() {

	}

	/**
	 * Cook all vegetables in salad.
	 * 
	 * @param salad - Salad for cooking.
	 */
	public static void cookSalad(Salad salad) {
		for (Vegetable vegetable : salad.getVegetables()) {
			vegetable.cook();
		}
	}

	/**
	 * Sorts an array of Vegetables.
	 * 
	 * @param salad - processed array.
	 */
	public static void sortSaladVegetables(Salad salad) {
		Arrays.sort(salad.getVegetables());
	}

	/**
	 * Searching by binary search for a specific vegetable in a salad
	 * 
	 * @param salad              - search salad
	 * @param searchingVegetable - the sought vegetable
	 * @return vegetable found in salad or NULL_VEGETABLE
	 */
	public static Vegetable searchVegetable(Salad salad, Vegetable searchingVegetable) {
		sortSaladVegetables(salad);
		return searchVegetableOnSortedArray(salad.getVegetables(), searchingVegetable);
	}

	/**
	 * Searching by binary search for a specific vegetable in a Vegetable array
	 * 
	 * @param vegetables         - search array of Vegatable
	 * @param searchingVegetable - the sought vegetable
	 * @return vegetable found in Vegetable array or NULL_VEGETABL
	 */
	private static Vegetable searchVegetableOnSortedArray(Vegetable[] vegetables, Vegetable searchingVegetable) {
		int foundIndex = Arrays.binarySearch(vegetables, searchingVegetable);
		if (foundIndex >= 0) {
			return vegetables[foundIndex];
		} else {
			return Vegetable.NULL_VEGETABLE;
		}
	}
}
