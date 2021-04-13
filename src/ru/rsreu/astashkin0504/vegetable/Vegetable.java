package ru.rsreu.astashkin0504.vegetable;

import com.prutzkow.resourcer.Resourcer;

/**
 * An abstract class describing the essence of a vegetable.
 */
public abstract class Vegetable implements Comparable<Vegetable> {

	/**
	 * Implementation of NullObject pattern
	 */
	public static final Vegetable NULL_VEGETABLE = new Vegetable() {
		@Override
		public void worseCondition() {

		}

		@Override
		public float getTotalCalories() {
			return 0;
		}

		@Override
		public String toString() {
			return "";
		}

		@Override
		public void cook() {
		}

		@Override
		protected float getCalorieContetPerHundredGramsByCooking() {
			return 0;
		}
	};

	/**
	 * The maximum calorie content of a vegetable per 100 grams (in kcal).
	 */
	public static final float MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS = 100;

	/**
	 * Maximum vegetable weight (in gram).
	 */
	public static final float MAX_WEIGHT = 1500;

	/**
	 * The default calorie content of a vegetable per 100 grams (in kcal).
	 */
	public static final float DEFAULT_CALORIE_CONTENT_PER_HUNDRED_GRAMS = 20;

	/**
	 * Default vegetable weight (in gram).
	 */
	public static final int DEFAULT_WEIGHT = 100;

	/**
	 * The number of grams for which the calories are distributed.
	 */
	protected static final int DISTRIBUTED_CALORIES_GRAMS = 100;

	/**
	 * Default value of cooked for vegetables.
	 */
	private static final CookingState DEFAULT_COOKING_STATE = CookingState.RAW;

	/**
	 * The degree of freshness of a vegetable.
	 */
	private Freshness statement;

	/**
	 * Calorie content per 100 grams of vegetable (in kcal).
	 */
	private float calorieContentPerHundredGrams = DEFAULT_CALORIE_CONTENT_PER_HUNDRED_GRAMS;

	/**
	 * Vegetable weight (in gram).
	 */
	private float weight = DEFAULT_WEIGHT;

	/**
	 * Cooking state of vegetable.
	 */
	private CookingState cookingState = DEFAULT_COOKING_STATE;

	/**
	 * Creates a Vegetable instance by 3 parameters.
	 * 
	 * @param statement                     - the degree of freshness of a
	 *                                      vegetable.
	 * @param calorieContentPerHundredGrams - calorie content per 100 grams of
	 *                                      vegetable (in kcal).
	 * @param weight                        - vegetable weight (in gram).
	 */
	public Vegetable(Freshness statement, float calorieContentPerHundredGrams, float weight) {
		this.statement = statement;
		if (isValidCalorieContentPerHundredGrams(calorieContentPerHundredGrams)) {
			if (isCalorieContentPerHundredGramsLessThanMaxValue(calorieContentPerHundredGrams)) {
				this.calorieContentPerHundredGrams = calorieContentPerHundredGrams;
			} else {
				this.calorieContentPerHundredGrams = MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS;
			}
		}
		if (isValidWeight(weight)) {
			if (isWeightLessThanMaxValue(weight)) {
				this.weight = weight;
			} else {
				this.weight = MAX_WEIGHT;
			}
		}
	}

	private Vegetable() {

	}

	/**
	 * Checks calorie content per hundred grams for admissible (from the point of
	 * view of logic).
	 * 
	 * @param calorieContentPerHundredGrams - checking value.
	 * @return true - if calorie content per hundred grams value is valid (>0).
	 */
	private static boolean isValidCalorieContentPerHundredGrams(float calorieContentPerHundredGrams) {
		return calorieContentPerHundredGrams > 0;
	}

	/**
	 * Checks that calorie content per hundred grams less than max value.
	 * 
	 * @param calorieContentPerHundredGrams - checking value.
	 * @return true - if calorie content per hundred grams less than max value.
	 */
	private static boolean isCalorieContentPerHundredGramsLessThanMaxValue(float calorieContentPerHundredGrams) {
		return calorieContentPerHundredGrams < MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS;
	}

	/**
	 * Checks weight for admissible (from the point of view of logic).
	 * 
	 * @param weight - checking value.
	 * @return true - if weight value is valid (>0).
	 */
	private static boolean isValidWeight(float weight) {
		return weight > 0;
	}

	/**
	 * Checks that weight less than max value.
	 * 
	 * @param weight - checking value.
	 * @return true - if weight than max value.
	 */
	private static boolean isWeightLessThanMaxValue(float weight) {
		return weight < MAX_WEIGHT;
	}

	/**
	 * @return degree of freshness of a vegetable.
	 */
	public Freshness getStatement() {
		return this.statement;
	}

	/**
	 * @return calorie content per 100 grams of vegetable (in kcal)
	 */
	public float getCalorieContentPerHundredGrams() {
		return this.calorieContentPerHundredGrams;
	}

	/**
	 * @return vegetable weight (in gram).
	 */
	public float getWeight() {
		return this.weight;
	}

	/**
	 * @return the calorie content of a vegetable in accordance with its weight.
	 */
	public float getTotalCalories() {
		return this.weight * this.calorieContentPerHundredGrams / DISTRIBUTED_CALORIES_GRAMS;
	}

	/**
	 * @return cooking state of vegetable.
	 */
	public CookingState getCookingState() {
		return this.cookingState;
	}

	/**
	 * Decreases the degree of freshness of the vegetable by 1 notch.
	 * 
	 * @throws WorstConditionException in case the vegetable is already in a worse
	 *                                 condition.
	 */
	public void worseCondition() throws WorstConditionException {
		if (statement.ordinal() < Freshness.values().length - 1) {
			if (this.cookingState == CookingState.COOKED) {
				this.statement = Freshness.values()[statement.ordinal() + 1];
			}
		} else {
			throw new WorstConditionException(Resourcer.getString("vegetables.Vegetable.exceptionMessage"));
		}
	}

	/**
	 * Cooks vegetable. Changes calories content by calculations of
	 * getCalorieContentPerHundredGramsByCooking().
	 * 
	 */
	public void cook() {
		this.cookingState = CookingState.COOKED;
		float newCalorieContent = this.getCalorieContetPerHundredGramsByCooking();
		if (isValidCalorieContentPerHundredGrams(newCalorieContent)) {
			if (isCalorieContentPerHundredGramsLessThanMaxValue(newCalorieContent)) {
				this.calorieContentPerHundredGrams = newCalorieContent;
			} else {
				this.calorieContentPerHundredGrams = MAX_CALORIE_CONTENT_PER_HUNDRED_GRAMS;
			}
		}
	}

	/**
	 * Calculates new calorie content after cooking.
	 * @return new calorie content
	 */
	protected abstract float getCalorieContetPerHundredGramsByCooking();

	/**
	 * The comparison is carried out according to the total calorie content of
	 * vegetables, if they are equal in weight.
	 */
	@Override
	public int compareTo(Vegetable o) {
		int result = ((Float) this.getTotalCalories()).compareTo(o.getTotalCalories());
		if (result == 0) {
			result = ((Float) this.weight).compareTo(o.weight);
		}
		return result;
	}

	/**
	 * @return string representation of a vegetable, for example: statement: *,
	 *         weight: *, total calorie: *.
	 */
	@Override
	public String toString() {
		return String.format(Resourcer.getString("vegetables.Vegetable.stringMessageFormat"),
				this.getClass().getSimpleName(), this.statement.toString(), this.weight,
				this.calorieContentPerHundredGrams, this.getTotalCalories(), this.cookingState);
	}
}
