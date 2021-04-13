package ru.rsreu.astashkin0504.vegetable;

import ru.rsreu.astashkin0504.RandomRangeGenerator;

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

	public void cook() {
		super.cook();
		if (this.getStatement() != Freshness.ROTTEN_STATEMENT) {
			float newCalorieContentPerHundredGrams = this.getCalorieContentPerHundredGrams()
					* this.getStatement().getCaloriesContentCoefficient()
					* RandomRangeGenerator.getRandomByRange(Freshness.MEDIUM_STATEMENT.getCaloriesContentCoefficient(),
							Freshness.FRESH_STATEMENT.getCaloriesContentCoefficient()) / 2;
			this.setCalorieContentPerHundredGrams(newCalorieContentPerHundredGrams);
		}
	}
}
