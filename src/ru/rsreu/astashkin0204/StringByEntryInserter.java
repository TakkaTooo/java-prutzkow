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
	 * @param input     - input string
	 * @param entry     - required number of entrys
	 * @param oldSubstr - desired substring
	 * @param newSubstr - substring to add
	 * @return Returns the modified string
	 * @throws IncorrectInputStringException
	 */
	public static String insertNewSubstrToInpString(String input, int entry, String oldSubstr, String newSubstr)
			throws IncorrectInputStringException {
		String output;
		if (input.isEmpty()) {
			throw new IncorrectInputStringException(InputStringExcCode.EMPTY);
		} else {
			// Find the position of the entry-th occurrence of the substring
			int currentPosition = getIndexOfEntryOccurence(input, oldSubstr, entry);
			currentPosition += oldSubstr.length() - 1; // Calculating the position from which to insert a new substring
			// Forming a new line
			// Copy the old string to the last occurrence of the substring
			// (including the last occurrence)
			output = input.substring(0, currentPosition);
			output += newSubstr; // Adding the inserted substring
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
	 * @param substr        - substring for search
	 * @param entryQuantity - required quantity of entrys substr in inputString
	 * @return Returns the index from which the entri-th occurrence of the substring
	 *         in the string begins.
	 * @throws IncorrectInputStringException
	 */
	private static int getIndexOfEntryOccurence(String input, String substr, int entryQuantity)
			throws IncorrectInputStringException {
		int entryQ = 0;
		int currentPosition = 0;
		// Iteration through the string until the index entry-th of the occurrence is
		// found or substring occurrences end at the current position
		while (entryQ != entryQuantity && (currentPosition = input.indexOf(substr, currentPosition)) != -1) {
			currentPosition++;
			entryQ++;
		}

		if (entryQ != entryQuantity || currentPosition == -1) {
			throw new IncorrectInputStringException(InputStringExcCode.NOT_ENOUGH_ENTRY);
		}

		return currentPosition;
	}
}
