package ru.rsreu.astashkin0804.datalayer;

public class DbTypeException extends RuntimeException {

	private static final long serialVersionUID = 8798858270524251851L;

	public DbTypeException() {
		super();
	}

	public DbTypeException(String message) {
		super(message);
	}
}
