package com.exercise.demo.exception;

public class NotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotSupportedException(String message) {
		super(message);
	}

}
