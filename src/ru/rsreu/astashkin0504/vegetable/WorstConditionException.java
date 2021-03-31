package ru.rsreu.astashkin0504.vegetable;

/**
 * Exception class to throw an error in case of an attempt to deteriorate
 * already in a worse state.
 */
public class WorstConditionException extends Exception {

	private static final long serialVersionUID = 4037595590354771118L;

	public WorstConditionException(String message) {
		super(message);
	}
}
