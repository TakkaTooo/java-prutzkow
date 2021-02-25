package ru.rsreu.astashkin0204;

import com.sun.istack.internal.NotNull;

/**
 * An exception-class for catching errors that occur when an invalid string is
 * passed to the input for methods of class StringByEntryInserter
 * 
 * @author Maxim Astashkin
 */
class IncorrectInputStringException extends Exception {

	private static final long serialVersionUID = -4745502473887350866L;

	/**
	 * Error code field, allows you to determine what exactly the line is incorrect
	 */
	@NotNull
	private final InputStringExcCode excCode;
	/**
	 * The string representation of the error
	 */
	private final String message;

	/**
	 * Constructs a new exception based on a specific error code (string
	 * incorrectness)
	 * 
	 * @param exC - error code
	 */
	IncorrectInputStringException(InputStringExcCode exC) {
		this.excCode = exC;
		if (this.excCode == InputStringExcCode.EMPTY) {
			this.message = "Exception. Input string is empty";
		} else /* this.excCode == InputStringExcCode.NOT_ENOUGH_ENTRY */ {
			this.message = "Exception. Input string does not contain the required number of occurrences.";
		}
	}

	public String toString() {
		return this.message;
	}
}