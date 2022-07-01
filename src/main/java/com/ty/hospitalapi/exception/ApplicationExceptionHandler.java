package com.ty.hospitalapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ty.hospitalapi.dto.ResponseStructure;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(value = NoIdFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noIdFoundExceptionHandler(NoIdFoundException ie) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("No id found");
		responseStructure.setData(ie.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidUserException.class)
	public ResponseEntity<ResponseStructure<String>> invalidUserException(InvalidUserException ie) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(ie.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
}
