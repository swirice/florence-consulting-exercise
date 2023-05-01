package com.exercise.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exercise.demo.dto.ErrorDTO;
import com.exercise.demo.exception.NoDataFoundException;
import com.exercise.demo.exception.NoResultsFoundException;
import com.exercise.demo.exception.NotSupportedException;
import com.exercise.demo.exception.ParsingException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoResultsFoundException.class)
	public ResponseEntity<Object> noResultsFoundExceptionHandler(NoResultsFoundException e) {
		HttpStatus httpStatus = HttpStatus.NO_CONTENT;
		return new ResponseEntity<>(new ErrorDTO(httpStatus, e.getClass().toString(), e.getMessage()), httpStatus);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> noDataFoundExceptionHandler(NoDataFoundException e) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(new ErrorDTO(httpStatus, e.getClass().toString(), e.getMessage()), httpStatus);
	}

	@ExceptionHandler(ParsingException.class)
	public ResponseEntity<Object> parsingExceptionHandler(ParsingException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(new ErrorDTO(httpStatus, e.getClass().toString(), e.getMessage()), httpStatus);
	}

	@ExceptionHandler(NotSupportedException.class)
	public ResponseEntity<Object> notSupportedExceptionHandler(NotSupportedException e) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(new ErrorDTO(httpStatus, e.getClass().toString(), e.getMessage()), httpStatus);
	}

}
