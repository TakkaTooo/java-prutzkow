package ru.rsreu.astashkin0504.vegetable;

import com.prutzkow.resourcer.Resourcer;

/**
 * Uses to display the freshness status of the Vegetable classes.
 */
public enum Freshness {

	FRESH_STATEMENT(Resourcer.getString("vegetables.Freshness.FRESH_STATEMENT.MESSAGE"), 1.5f),
	MEDIUM_STATEMENT(Resourcer.getString("vegetables.Freshness.MEDIUM_STATEMENT.MESSAGE"), 1.2f),
	ROTTEN_STATEMENT(Resourcer.getString("vegetables.Freshness.ROTTEN_STATEMENT.MESSAGE"), 1.1f);

	/**
	 * String value describing the freshness state.
	 */
	private final String message;

	/**
	 * Coefficient of calories content increasing after cooking for Vegetable with
	 * specific state.
	 */
	private final float caloriesContentCoefficient;

	Freshness(String message, float caloriesContentCoefficient) {
		this.message = message;
		this.caloriesContentCoefficient = caloriesContentCoefficient;
	}

	public float getCaloriesContentCoefficient() {
		return this.caloriesContentCoefficient;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
