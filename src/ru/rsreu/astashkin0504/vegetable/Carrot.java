package ru.rsreu.astashkin0504.vegetable;

public class Carrot extends Vegetable {

	/**
	 * Creates a Carrot instance by 3 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 */
	public Carrot(Freshness statement, int calorieContentPerHundredGrams, int weight) {
		super(statement, calorieContentPerHundredGrams, weight);
	}

	@Override
	protected float getCalorieContetPerHundredGramsByCooking() {
		float newCalorieContentPerHundredGrams = 0;
		if (this.getStatement() == Freshness.FRESH_STATEMENT) {
			newCalorieContentPerHundredGrams = (float) (Math.pow(this.getStatement().getCaloriesContentCoefficient(), 2)
					* this.getCalorieContentPerHundredGrams());
		}
		return newCalorieContentPerHundredGrams;
	}
}
