package com.exercise.demo.exception;

public class NoResultsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoResultsFoundException(String message) {
		super(message);
	}

}
