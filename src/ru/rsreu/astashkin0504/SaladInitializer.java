package ru.rsreu.astashkin0504;
import ru.rsreu.astashkin0504.vegetables.*;

public class SaladInitializer {

	private static final boolean TOMATO_COOKED_STATE = true;
	private static final Vegetable CARROT = new Carrot(Freshness.FRESH_STATEMENT, 25, 150);
	private static final Vegetable CUCUMBER = new Cucumber(Freshness.MEDIUM_STATEMENT, 15, 80);
	private static final Vegetable TOMATO = new Tomato(Freshness.FRESH_STATEMENT, 20, 100, TOMATO_COOKED_STATE);
	
	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private SaladInitializer() {
		
	}

	/**
	 * Creates an array of Vegetable instaces.
	 * @return Vegetable[].
	 */
	public static Vegetable[] getSalad() {
		return new Vegetable[] {
			CARROT,
			CUCUMBER,
			TOMATO
		};
	}

}
