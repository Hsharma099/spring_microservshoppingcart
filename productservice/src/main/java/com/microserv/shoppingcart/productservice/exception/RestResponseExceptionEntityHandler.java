package com.microserv.shoppingcart.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microserv.shoppingcart.productservice.model.ErrorResponse;

@ControllerAdvice
public class RestResponseExceptionEntityHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> productServiceExceptionHandler(ProductServiceCustomException customException){
		
		ErrorResponse errorResponse =  ErrorResponse.builder()
				                            .errorCode(customException.getErrorCode())
				                            .errorMessage(customException.getMessage())
				                            .build();
		
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		
		
	}

}
