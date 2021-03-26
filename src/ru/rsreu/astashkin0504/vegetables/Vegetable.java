package ru.rsreu.astashkin0504.vegetables;

import com.prutzkow.resourcer.Resourcer;

/**
 * An abstract class describing the essence of a vegetable.
 */
public abstract class Vegetable implements Comparable<Vegetable> {

	/**
	 * The maximum calorie content of a vegetable per 100 grams (in kcal).
	 */
	public static final int MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS = 100;

	/**
	 * Maximum vegetable weight (in gram).
	 */
	public static final int MAX_WEIGHT = 1500;

	/**
	 * The default calorie content of a vegetable per 100 grams (in kcal).
	 */
	public static final int DEFAULT_CALORIE_CONTENT_PER_HUNDRED_GRAMS = 20;

	/**
	 * Default vegetable weight (in gram).
	 */
	public static final int DEFAULT_WEIGHT = 100;

	/**
	 * The number of grams for which the calories are distributed.
	 */
	private static final int DISTRIBUTED_CALORIES_GRAMS = 100;

	/**
	 * The degree of freshness of a vegetable.
	 */
	private Freshness statement;

	/**
	 * Calorie content per 100 grams of vegetable (in kcal).
	 */
	private int calorieContentPerHundredGrams = DEFAULT_CALORIE_CONTENT_PER_HUNDRED_GRAMS;

	/**
	 * Vegetable weight (in gram).
	 */
	private int weight = DEFAULT_WEIGHT;

	/**
	 * Creates a Vegetable instance by 3 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 */
	public Vegetable(Freshness statement, int calorieContentPerHundredGrams, int weight) {
		this.statement = statement;
		if (calorieContentPerHundredGrams > 0
				&& calorieContentPerHundredGrams <= MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS) {
			this.calorieContentPerHundredGrams = calorieContentPerHundredGrams;
		}
		if (weight > 0 && weight <= MAX_WEIGHT) {
			this.weight = weight;
		}
	}

	/**
	 * @return degree of freshness of a vegetable.
	 */
	public Freshness getStatement() {
		return this.statement;
	}

	/**
	 * 
	 * 
	 * @return calorie content per 100 grams of vegetable (in kcal)
	 */
	public int getCalorieContentPerHundredGrams() {
		return this.calorieContentPerHundredGrams;
	}

	/**
	 * 
	 * @return vegetable weight (in gram).
	 */
	public float getWeight() {
		return this.weight;
	}

	/**
	 * Decreases the degree of freshness of the vegetable by 1 notch.
	 * 
	 * @throws WorstConditionException in case the vegetable is already in a worse
	 *                                 condition.
	 */
	public void worseCondition() throws WorstConditionException {
		if (statement.ordinal() < Freshness.values().length - 1) {
			this.statement = Freshness.values()[statement.ordinal() + 1];
		} else {
			throw new WorstConditionException(Resourcer.getString("vegetables.Vegetable.exceptionMessage"));
		}
	}

	/**
	 * @return the calorie content of a vegetable in accordance with its weight.
	 */
	public int getTotalCalories() {
		return this.weight * this.calorieContentPerHundredGrams / DISTRIBUTED_CALORIES_GRAMS;
	}

	/**
	 * The comparison is carried out according to the total calorie content of
	 * vegetables, if they are equal in weight.
	 */
	@Override
	public int compareTo(Vegetable o) {
		int result = ((Integer) this.getTotalCalories()).compareTo(o.getTotalCalories());
		if (result == 0) {
			result = ((Integer) this.weight).compareTo(o.weight);
		}
		return result;
	}

	/**
	 * @return string representation of a vegetable, for example: statement: *,
	 *         weight: *, total calorie: *.
	 */
	@Override
	public String toString() {
		return String.format(Resourcer.getString("vegetables.Vegetable.stringMessage"), this.statement.toString(),
				this.weight, this.getTotalCalories());
	}
}
