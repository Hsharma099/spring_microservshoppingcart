package com.springudemy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springudemy.model.ErrorMessage;

@ControllerAdvice
/*
 *  It is typically used to centralize exception handling logic
 *  and reduce code duplication across controllers. 
 *  When an exception occurs during the execution of a controller method, 
 *  @ControllerAdvice-annotated classes can handle the exception and 
 *  return an appropriate response to the client. 
 */
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	/*
	 * This method is used for handle exception of employeeNotFound
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage employeeNotFoundHandler(EmployeeNotFoundException exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return errorMessage;
		
	}
	
	/*
	 * This method is used for handling generic exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage genericExceptionHandler(Exception exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage());
		return errorMessage;
		
	}
	
	

}
