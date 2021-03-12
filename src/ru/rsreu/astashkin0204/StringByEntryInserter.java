package ru.rsreu.astashkin0204;

/**
 * A utility class that has a method for adding the substring newSubstr to the
 * input string after the entry-th occurrence of the substring oldSubstr
 * 
 * @author Maxim Astashkin
 *
 */
public class StringByEntryInserter {

	/**
	 * Default private constructor so that you cannot create instances of the
	 * utility class
	 */
	private StringByEntryInserter() {

	}

	/**
	 * Adds the substring newSubstr to the input string after the entry-th
	 * occurrence of the substring oldSubstr.
	 * 
	 * @param input        - input string
	 * @param entry        - required number of entrys
	 * @param oldSubstring - desired substring
	 * @param newSubstring - substring to add
	 * @return Returns the modified string
	 * @throws IncorrectInputStringException
	 */
	public static String insertNewSubstringToInputString(String input, int entry, String oldSubstring,
			String newSubstring) throws IncorrectInputStringException {
		String output;
		if (input.isEmpty()) {
			throw new IncorrectInputStringException(InputStringExceptionCode.EMPTY);
		} else {
			// Find the position of the entry-th occurrence of the substring
			int currentPosition = getIndexOfEntryOccurence(input, oldSubstring, entry);
			currentPosition += oldSubstring.length() - 1; // Calculating the position from which to insert a new
															// substring
			// Forming a new line
			// Copy the old string to the last occurrence of the substring
			// (including the last occurrence)
			output = input.substring(0, currentPosition);
			output += newSubstring; // Adding the inserted substring
			if (currentPosition != input.length() - 1) {
				// If entry-th entry is not the end of the input line
				output += input.substring(currentPosition, input.length()); // Adding the end of the input line
			}
		}
		return output;
	}

	/**
	 * Searches the index of the entry-th occurrence of a substring in a string
	 * 
	 * @param input         - input string
	 * @param substring     - substring for search
	 * @param entryQuantity - required quantity of entrys substr in inputString
	 * @return Returns the index from which the entri-th occurrence of the substring
	 *         in the string begins.
	 * @throws IncorrectInputStringException
	 */
	private static int getIndexOfEntryOccurence(String input, String substring, int entryQuantity)
			throws IncorrectInputStringException {
		int currentEntryQantity = 0;
		int currentPosition = 0;
		// Iteration through the string until the index entry-th of the occurrence is
		// found or substring occurrences end at the current position
		while (!isLastOrSufficientEntrySubstringInString(currentPosition, currentEntryQantity, entryQuantity, input,
				substring)) {
			currentPosition = input.indexOf(substring, currentPosition);
			currentPosition++;
			currentEntryQantity++;
		}

		if (currentEntryQantity != entryQuantity) {
			throw new IncorrectInputStringException(InputStringExceptionCode.NOT_ENOUGH_ENTRY);
		}

		return currentPosition;
	}

	/**
	 * Returns true - in case the current Quantity is the required number of
	 * occurrences or the occurrences of a substring in the string have ended
	 * 
	 * @param currentPositionInString - current position for checking substring in
	 *                                string
	 * @param currentEntryQuantity    - current quantity of substring entries
	 * @param requiredEntryQuantity   - required qunaitity of substring entries
	 * @param checking                - the string being checked for occurrences of
	 *                                a substring
	 * @param substring               - the substring to checked for occurrences in
	 *                                the string
	 */
	private static boolean isLastOrSufficientEntrySubstringInString(int currentPositionInString,
			int currentEntryQuantity, int requiredEntryQuantity, String checking, String substring) {
		return currentEntryQuantity == requiredEntryQuantity
				|| checking.indexOf(substring, currentPositionInString) == -1;
	}
}
