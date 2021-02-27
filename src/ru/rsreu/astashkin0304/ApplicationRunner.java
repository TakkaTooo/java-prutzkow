package ru.rsreu.astashkin0304;

import com.prutzkow.resourcer.Resourcer;
import java.util.Locale;

public class ApplicationRunner {

	/**
	 * Default private constructor so that you cannot create instances of the
	 * utility class
	 */
	private ApplicationRunner() {

	}

	/**
	 * Entry point
	 * 
	 * @param args - Launch parameters
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		StringBuilder output = new StringBuilder();
		ArrayAdjacentElementsInPairSwaper array = new ArrayAdjacentElementsInPairSwaper(1, 2, 5, 6, 11, 9,
				-1, 6, 3);
		output.append(Resourcer.getString("message.array.before"))
				.append(array.toString())
				.append("\n");

		output.append(Resourcer.getString("message.numberOfSwaps"))
				.append(array.swapAdjacentElemetnsInPair())
				.append("\n");
		output.append(Resourcer.getString("message.array.after"))
				.append(array.toString())
				.append("\n");

		System.out.print(output);

	}

}
