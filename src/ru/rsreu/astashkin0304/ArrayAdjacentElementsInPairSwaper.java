package ru.rsreu.astashkin0304;

import java.util.Arrays;

public class ArrayAdjacentElementsInPairSwaper {

	/**
	 * Field - container array of integer elements
	 */
	private int[] arrayContainer;

	/**
	 * Constructs a new object, initializes arrayContainer by args;
	 * 
	 * @param args - - elements for array
	 */
	public ArrayAdjacentElementsInPairSwaper(int... args) {
		this.arrayContainer = new int[args.length];
		setArrayContainer(args);
	}

	/**
	 * Initializes arrayContainer by args
	 * 
	 * @param args - elements for array
	 */
	public final void setArrayContainer(int... args) {
		int len = args.length;
		for (int i = 0; i < len; ++i) {
			this.arrayContainer[i] = args[i];
		}
	}

	/**
	 * Swap adjacent elements in pairs of arrayContainer (1st and 2nd, 3rd and 4th,
	 * etc.)
	 */
	public void swapAdjacentElemetnsInPair() {
		int buffer; // buffer var for swap
		int len = this.arrayContainer.length;
		for (int i = 0; i < len - 1; i += 2) {
			buffer = this.arrayContainer[i];
			this.arrayContainer[i] = this.arrayContainer[i + 1];
			this.arrayContainer[i + 1] = buffer;
		}
	}

	/**
	 * Compares elements of arrayContainer with given indexes
	 * 
	 * @param firstIndex  - index in arrayContainer of first element
	 * @param secondIndex - index in arrayContainer of second element
	 * @return true - if element with firstIndex is less than elements with
	 *         secondIndex
	 */
	private boolean isFirstLessThanSecond(int firstIndex, int secondIndex) {
		return this.arrayContainer[firstIndex] < this.arrayContainer[secondIndex];
	}

	/**
	 * @return Quantity of pairs, where left element less than right.
	 */
	public int countPairsWithLeftLessThanRight() {
		int quantityLeftLessThanRight = 0; // quantity for counting initial pairs in which the left element is less than
											// the first one
		int len = this.arrayContainer.length;
		for (int i = 0; i < len - 1; i += 2) {
			if (isFirstLessThanSecond(i, i + 1)) {
				quantityLeftLessThanRight++;
			}
		}
		return quantityLeftLessThanRight;
	}

	/**
	 * @return a string representation of the object, an array as "[1, 2, 3, 4]" for
	 *         example
	 */
	@Override
	public String toString() {
		return Arrays.toString(this.arrayContainer);
	}
}
