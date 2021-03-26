package ru.rsreu.astashkin0504.vegetables;

import com.prutzkow.resourcer.Resourcer;

public class Cucumber extends Vegetable {

	/**
	 * Creates a Cucumber instance by 3 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 */
	public Cucumber(Freshness statement, int calorieContentPerHundredGrams, int weight) {
		super(statement, calorieContentPerHundredGrams, weight);
	}

	@Override
	public String toString() {
		return String.format(Resourcer.getString("vegetables.Cucumber.stringMessage"), super.toString());
	}
}
