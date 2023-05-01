package com.exercise.demo.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorDTO {

	private HttpStatus httpStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Rome")
	private LocalDateTime timestamp;

	private String message;

	private Object details;

	public ErrorDTO(HttpStatus httpStatus, String message, Object details) {
		this.httpStatus = httpStatus;
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}

}