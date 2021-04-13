package ru.rsreu.astashkin0504;
import ru.rsreu.astashkin0504.vegetable.*;

public class VegetablesInitializer {

	private static final Vegetable CARROT = new Carrot(Freshness.FRESH_STATEMENT, 25, 162);
	private static final Vegetable CUCUMBER = new Cucumber(Freshness.MEDIUM_STATEMENT, 15, 80);
	private static final Vegetable TOMATO = new Tomato(Freshness.FRESH_STATEMENT, 20, 100);
	
	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private VegetablesInitializer() {
		
	}

	/**
	 * Creates an array of Vegetable instances.
	 * @return Vegetable[].
	 */
	public static Vegetable[] initializeVegetablesArray() {
		return new Vegetable[] {
			CARROT,
			CUCUMBER,
			TOMATO
		};
	}
	
	/**
	 * @return Specific Tomato instance for next searching
	 */
	public static Vegetable getSearchingVegetableInstance() {
		return TOMATO;
		//return TOMATO;
	}
}
