package com.microserv.shoppingcart.OrderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.microserv.shoppingcart.OrderService.external.response.ErrorResponse;
@ControllerAdvice
public class RestResponseExceptionEntityHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException customException){
		
		ErrorResponse errorResponse =  ErrorResponse.builder()
				                            .errorCode(customException.getErrorCode())
				                            .errorMessage(customException.getMessage())
				                            .build();
		
		return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(customException.getStatus()));
		
		
	}

}
