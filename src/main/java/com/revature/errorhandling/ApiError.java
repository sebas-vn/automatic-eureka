package com.revature.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data // all getters and setters
public class ApiError {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private int status;
	
	private String error;
	
	private String message;
	private String debugMessage;
	
	
	List<ApiSubError> subErrors = new ArrayList<>();
	
	public ApiError() {
		super();
		this.timestamp = LocalDateTime.now();
	}
	
	public void addSubError(ApiSubError error) {
		this.subErrors.add(error);
	}
	
	public ApiError(HttpStatus status) {
		super();
		this.status = status.value();
		this.error = status.getReasonPhrase();
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		this(status); // this is constrcutor chaining -- we are doing everything above constrcutor is doing with this 
		this.message = "No message available";
		this.debugMessage = ex.getLocalizedMessage(); // now we dont have to look at our console to figure out why thoings are going wrong
	}
	
	public ApiError(HttpStatus status, String message, Throwable ex) {
		this.message = message;
	}
}
