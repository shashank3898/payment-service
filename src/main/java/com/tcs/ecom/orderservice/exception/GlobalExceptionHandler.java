package com.tcs.ecom.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<?> handleNotFound(OrderNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(ex.getMessage()));
	}

	@ExceptionHandler(InvalidOrderStatusException.class)
	public ResponseEntity<?> handleInvalidStatus(InvalidOrderStatusException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error(ex.getMessage()));
	}

	private Map<String, Object> error(String message) {
		return Map.of("timestamp", LocalDateTime.now(), "error", message);
	}
}
