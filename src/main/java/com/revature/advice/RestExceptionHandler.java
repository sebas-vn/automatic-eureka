package com.revature.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.errorhandling.ApiError;
import com.revature.errorhandling.ApiValidationError;

// Tell spring that this Advice is going to intercept all HTTP requests that hit our Controller

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	// Custom method to send back the api error as response entity
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	/**
	 * 
	 * What happens if a User uses a POST request to send and INVALID User object
	 * whuch defies some ValidationThat we set up in the model
	 * 
	 */
	
	
	// This is designed to capture any exception that might occur when a controller mathod takes in a "bad" object
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		String error = "Request failed validation";
		
		// instantiate an ApiError object
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error, ex);
		
		// next we can find out what went wrong
		
		// 1. capture the MethodArgumentNotValidException and iterate over all the field errors
		for(FieldError e : ex.getFieldErrors()) {
			apiError.addSubError(new ApiValidationError(e.getObjectName(), e.getDefaultMessage(), e.getField(), e.getRejectedValue()));
		}
		
		
		// send the api error as the response entity
		return buildResponseEntity(apiError);
	}
	

	/**
	 * Intercept exceptions that are caused by Invalid JSON
	 * Send back 4xx indicating client side error
	 */
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		
		String error = "Malformed JSON request!";
		

		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}
	

}
