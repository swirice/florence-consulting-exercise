package com.exercise.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorDTO {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	private HttpStatus httpStatus;

	private String exceptionType;

	private String message;

	public ErrorDTO(HttpStatus httpStatus, String exceptionType, String message) {
		this.timestamp = LocalDateTime.now();
		this.httpStatus = httpStatus;
		this.exceptionType = exceptionType;
		this.message = message;
	}

}