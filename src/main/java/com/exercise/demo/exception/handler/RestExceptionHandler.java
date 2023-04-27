package com.exercise.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exercise.demo.exception.NoDataFoundException;
import com.exercise.demo.exception.NoResultsFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoResultsFoundException.class)
	public ResponseEntity<Object> noResultsFoundExceptionHandler(NoResultsFoundException e) {
		return new ResponseEntity<>(new NoResultsFoundException(e.getMessage()), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> noDataFoundExceptionHandler(NoDataFoundException e) {
		return new ResponseEntity<>(new NoDataFoundException(e.getMessage()), HttpStatus.NOT_FOUND);
	}

}