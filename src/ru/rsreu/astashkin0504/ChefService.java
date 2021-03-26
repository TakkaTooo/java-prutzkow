package ru.rsreu.astashkin0504;

import java.util.Arrays;

import ru.rsreu.astashkin0504.vegetables.*;

public class ChefService {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ChefService() {

	}

	public static Vegetable[] createSalad() {
		return SaladInitializer.getSalad();
	}

	/**
	 * Calculates the total calorie content of the salad, taking into account the
	 * calorie content of all vegetables.
	 * 
	 * @param salad - Vegetable's array.
	 * @return total calorie content of salad.
	 */
	public static int getSaladCalorieContent(Vegetable[] salad) {
		int summ = 0;
		for (Vegetable vegetable : salad) {
			summ += vegetable.getTotalCalories();
		}
		return summ;
	}

	/**
	 * @param salad - Vegetable's array.
	 * @return a string representation of the salad.
	 */
	public static String getStringSaladRepresentation(Vegetable[] salad) {
		StringBuilder output = new StringBuilder();
		for (Vegetable vegetable : salad) {
			output.append(vegetable.toString()).append("\n");
		}
		return output.toString();
	}
	
	/**
	 * Sorts an array of Vegetables.
	 * @param salad - processed array.
	 */
	public static void sortSaladVegetables(Vegetable[] salad) {
		Arrays.sort(salad);
	}
}
