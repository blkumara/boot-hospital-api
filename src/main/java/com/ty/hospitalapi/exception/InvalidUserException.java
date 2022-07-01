package com.ty.hospitalapi.exception;

public class InvalidUserException extends RuntimeException {

	String message = "Invalid user";

	public InvalidUserException() {

	}

	public InvalidUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
