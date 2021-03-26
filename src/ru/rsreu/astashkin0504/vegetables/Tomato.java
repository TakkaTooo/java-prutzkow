package ru.rsreu.astashkin0504.vegetables;

import com.prutzkow.resourcer.Resourcer;

public class Tomato extends Vegetable {

	/**
	 * Default value for isCooked.
	 */
	private static final boolean DEFAULT_COOKED_STATE = false;

	/**
	 * Ñalorie supplement if the vegetable is cooked.
	 */
	private static final int CALORIE_PER_HUNDRED_GRAMS_COOKED_SUPPLEMENT = 10;

	/**
	 * Shows whether the tomato is cooked or not. true - cooked, false - not cooked.
	 */
	private boolean isCooked = DEFAULT_COOKED_STATE;

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

	/**
	 * Creates a Tomato instance by 4 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 * @param isCooked                      - shows whether the tomato is cooked or
	 *                                      not.
	 */
	public Tomato(Freshness statement, int calorieContentPerHundredGrams, int weight, boolean isCooked) {
		super(statement, calorieContentPerHundredGrams, weight);
		this.isCooked = isCooked;
	}

	/**
	 * @return the calorie supplement depending on the readiness of the tomato.
	 */
	private int getCaloriePerHundredGramsCookedSupplement() {
		int supplement;
		if (this.isCooked) {
			supplement = CALORIE_PER_HUNDRED_GRAMS_COOKED_SUPPLEMENT;
		} else {
			supplement = 0;
		}
		return supplement;
	}

	@Override
	public int getTotalCalories() {
		return super.getTotalCalories() + this.getCaloriePerHundredGramsCookedSupplement();
	}

	@Override
	public String toString() {
		return String.format(Resourcer.getString("vegetables.Tomato.stringMessage"), super.toString());
	}
}
