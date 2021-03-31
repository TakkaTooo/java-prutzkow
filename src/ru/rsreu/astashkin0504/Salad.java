package ru.rsreu.astashkin0504;

import ru.rsreu.astashkin0504.vegetable.Vegetable;

public class Salad {

	private Vegetable[] vegetables;

	public Salad(Vegetable... vegetables) {
		this.vegetables = vegetables;
	}

	/**
	 * Calculates the total calorie content of the salad, taking into account the
	 * calorie content of all vegetables.
	 * 
	 * @param salad - Vegetable's array.
	 * @return total calorie content of salad.
	 */
	public int getTotalCaloriesContent() {
		int summ = 0;
		for (Vegetable element : this.vegetables) {
			summ += element.getTotalCalories();
		}
		return summ;
	}

	/**
	 * Returns all salad vegetables
	 * 
	 * @return array body for salad vegetable[]
	 */
	public Vegetable[] getVegetables() {
		return this.vegetables;
	}

	/**
	 * Returns a specific vegetable
	 * 
	 * @param index - vegetable index id array body
	 * @return Vegetable from array by index
	 * @throws ArrayIndexOutOfBoundsException in case the index is passed outside
	 *                                        the array boundaries
	 */
	public Vegetable getVegetabeFromSaladByIndex(int index) throws ArrayIndexOutOfBoundsException {
		return this.vegetables[index];
	}

	/**
	 * @return string representation of salad
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Vegetable element : this.vegetables) {
			output.append(element).append("\n");
		}
		return output.toString();
	}

}
