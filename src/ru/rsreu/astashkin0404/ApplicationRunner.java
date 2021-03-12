package ru.rsreu.astashkin0404;

import com.prutzkow.resourcer.Resourcer;

/**
 * The class is the entry point for the application that implements the 4
 * practical tasks.
 * 
 * @author Maxim Astashkin
 *
 */
public class ApplicationRunner {
	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private ApplicationRunner() {

	}

	/**
	 * Constant field storing the lengths of strings of a two-dimensional array.
	 */
	private static final int[] ROWS_SIZES = { 1, 2, 3, 4, 5, 6, 0, 6, 5, 4, 3, 2, 1 };

	/**
	 * A constant field that sets the lower bound for generating random numbers to
	 * fill a two-dim array.
	 */
	private static final int MIN_NUMBER = 0;

	/**
	 * A constant field that sets the higher bound for generating random numbers to
	 * fill a two-dim array.
	 */
	private static final int MAX_NUMBER = 10;
	
	/**
	 * Entry point.
	 * @param args - Launch parameters.
	 */
	public static void main(String[] args) {
		StringBuilder output = new StringBuilder();
		RowsMaxLengthSameElementsSequenceArray array = new RowsMaxLengthSameElementsSequenceArray(ROWS_SIZES);
		array.fill(MIN_NUMBER, MAX_NUMBER);
		int[] rows = array.getMaxLengthSameElementsSequenceRows();

		output.append(Resourcer.getString("message.sourceArray"))
				.append("\n").append(array.toString());
		output.append(Resourcer.getString("message.infoAboutTask"));
		for (int item : rows) {
			output.append(item).append(" ");
		}
		System.out.println(output);
	}

}
