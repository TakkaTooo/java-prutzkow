package ru.rsreu.astashkin0504.vegetable;

public class Tomato extends Vegetable {

	/**
	 * Creates a Tomato instance by 3 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 */
	public Tomato(Freshness statement, int calorieContentPerHundredGrams, int weight) {
		super(statement, calorieContentPerHundredGrams, weight);
	}

	@Override
	protected float getCalorieContetPerHundredGramsByCooking() {
		return (float) (this.getStatement().getCaloriesContentCoefficient()
				* Math.pow(this.getCalorieContentPerHundredGrams(), 1.1f));
	}
}
