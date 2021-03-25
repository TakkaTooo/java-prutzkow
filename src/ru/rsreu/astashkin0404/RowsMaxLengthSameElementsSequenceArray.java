package ru.rsreu.astashkin0404;

import com.prutzkow.twodimarray.*;

import com.prutzkow.resourcer.Resourcer;

public class RowsMaxLengthSameElementsSequenceArray extends TwoDimArray {

	/**
	 * Creates a new instance with an two-dim array, with the specified string
	 * lengths.
	 * 
	 * @param sizes - Two-dim array string lengths.
	 */
	public RowsMaxLengthSameElementsSequenceArray(int... sizes) throws IllegalArgumentException {
		super(sizes);
	}

	/**
	 * Fills an array of an object with random numbers generated in a range [min,
	 * max] (inclusive).
	 * 
	 * @param min - lower bound for generating random numbers (inclusive).
	 * @param max - higher bound for generating random numbers (inclusive).
	 * @throws IllegalArgumentException in case if min mor than max.
	 */
	public void fill(int min, int max) throws IllegalArgumentException {
		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 0; j < this.arrayBody[i].length; j++) {
				this.arrayBody[i][j] = RandomRangeGenerator.getRandomByRange(min, max);
			}
		}
	}

	/**
	 * @return An array at the indices of the strings that contain the longest
	 *         sequences of elements with the same value.
	 */
	public int[] getMaxLengthSameElementsSequenceRows() {
		int[] sequenceLenghts = getLengthsSameElementsSequenceByRows();
		int maxLengthSequence = getArrayMaxValue(sequenceLenghts);
		return IndecesWithQuantityArrayElementsEqaulsValueUtiliter.getIndecesElementsEqualsValue(sequenceLenghts,
				maxLengthSequence);
	}

	/**
	 * @return An array with the lengths of sequences of elements with the same
	 *         values (array index is the index of the string in the array of this
	 *         object).
	 */
	private int[] getLengthsSameElementsSequenceByRows() {
		int[] sequenceLengths = new int[this.getRowCount()];
		for (int i = 0; i < this.getRowCount(); i++) {
			sequenceLengths[i] = this.geMaxLengthSequenceInRow(this.arrayBody[i]);
		}
		return sequenceLengths;
	}

	/**
	 * 
	 * @param row - array to search for sequences.
	 * @return The length of the longest sequence of elements with the same value in
	 *         array (row of two-dim array).
	 */
	private int geMaxLengthSequenceInRow(int[] row) {
		int length = 1;
		int maxLength = 1;
		for (int i = 0; i < row.length - 1; i++) {
			if (row[i] == row[i + 1]) {
				if (++length > maxLength) {
					maxLength = length;
				}
			} else {
				length = 1;
			}
		}
		return maxLength;
	}

	/**
	 * @return number number of elements in the first column
	 */
	private int countElementsInColumn(int column) {
		int quantity = 0;
		for (int i = 0; i < this.getRowCount(); i++) {
			if (this.arrayBody[i].length >= column) {
				quantity++;
			}
		}
		return quantity;
	}

	/**
	 * @param values - input array.
	 * @return max value in values array.
	 */
	private static int getArrayMaxValue(int[] values) {
		int maxValue = values[0];
		for (int item : values) {
			if (item > maxValue) {
				maxValue = item;
			}
		}
		return maxValue;
	}

	/**
	 * Adds information about the number of elements in the first column to the
	 * string representation of an array.
	 * 
	 * @return A string representation of a two-dimensional array and information
	 *         about the number of elements in the first column.
	 */
	@Override
	public String toString() {
		return String.format("%s%s %d\n", super.toString(), Resourcer.getString("message.rowCount"),
				countElementsInColumn(2));
	}
}
