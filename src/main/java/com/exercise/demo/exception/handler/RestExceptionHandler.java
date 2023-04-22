package com.exercise.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exercise.demo.exception.NoContentException;
import com.exercise.demo.exception.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Object> noContentExceptionHandler(NoContentException e) {
		return new ResponseEntity<>(new NoContentException(e.getMessage()), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException e) {
		return new ResponseEntity<>(new NotFoundException(e.getMessage()), HttpStatus.NOT_FOUND);
	}

}
