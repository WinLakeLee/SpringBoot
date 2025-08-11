package com.example.board;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class BoardExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public String globalExceptionHandler (Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}