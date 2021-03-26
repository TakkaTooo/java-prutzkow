package ru.rsreu.astashkin0504.vegetables;

import com.prutzkow.resourcer.Resourcer;

/**
 * Uses to display the freshness status of the Vegetable classes.
 */
public enum Freshness {

	FRESH_STATEMENT(Resourcer.getString("vegetables.Freshness.FRESH_STATEMENT.MESSAGE")),
	MEDIUM_STATEMENT(Resourcer.getString("vegetables.Freshness.MEDIUM_STATEMENT.MESSAGE")),
	ROTTEN_STATEMENT(Resourcer.getString("vegetables.Freshness.ROTTEN_STATEMENT.MESSAGE"));

	/**
	 * String value describing the freshness state.
	 */
	private final String message;

	Freshness(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
