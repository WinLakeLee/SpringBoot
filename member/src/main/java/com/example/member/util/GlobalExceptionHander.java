package com.example.member.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.persistence.EntityExistsException;

@ControllerAdvice
public class GlobalExceptionHander {

	public ResponseEntity<String> handleEntityExistsException (EntityExistsException e) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
							 .body(e.getMessage());
	}
}
