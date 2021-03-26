package ru.rsreu.astashkin0404;

/**
 * Helper utility class for solving the problem of finding line numbers with the
 * longest sequence of identical characters.
 * 
 * @author Maxim Astashkin
 */
public class IndecesWithQuantityArrayElementsEqaulsValueUtiliter {

	/**
	 * Default private constructor - so that you cannot create instances of the
	 * utility class.
	 */
	private IndecesWithQuantityArrayElementsEqaulsValueUtiliter() {

	}

	/**
	 * @param values - processed array.
	 * @param value  - value to compare.
	 * @return array of element indices that are equal to value.
	 */
	public static int[] getIndecesElementsEqualsValue(int[] values, int value) {
		int quantity = getQuantityElementsEqualValue(values, value);
		return getIndecesElementsEqualsValue(values, value, quantity);
	}

	/**
	 * This method not be used outside this class due to the inconvenient signature
	 * with the transfer of a quantity unknown in advance.
	 * 
	 * @param values   - processed array.
	 * @param value    - value to compare.
	 * @param quantity - amount of elements.
	 * @return array of element indices that are equal to value.
	 */
	private static int[] getIndecesElementsEqualsValue(int[] values, int value, int quantity) {
		int[] indeces = new int[quantity];
		if (quantity > 0) {
			int length = values.length;
			int currentIndexNumber = 0;
			for (int i = 0; i < length && currentIndexNumber < quantity; i++) {
				if (values[i] == value) {
					indeces[currentIndexNumber++] = i;
				}
			}
		}
		return indeces;
	}

	/**
	 * @param values - processed array.
	 * @param value  - value to compare.
	 * @return - quantity of elements that are equal to value.
	 */
	private static int getQuantityElementsEqualValue(int[] values, int value) {
		int quantity = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == value) {
				quantity++;
			}
		}
		return quantity;
	}
}
