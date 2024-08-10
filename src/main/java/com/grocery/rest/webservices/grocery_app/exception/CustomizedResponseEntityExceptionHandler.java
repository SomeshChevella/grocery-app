package com.grocery.rest.webservices.grocery_app.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.grocery.rest.webservices.grocery_app.product.ProductNotFoundException;
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> hanldeAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorDetails getErrorDetails(Exception ex, WebRequest request) {
		return new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleProductNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	    List<FieldError> fieldErrors = ex.getFieldErrors();
	    String errorMessage = fieldErrors.stream()
	            .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
	            .collect(Collectors.joining(";"));

	    ErrorDetails errorDetails = new ErrorDetails(
	            LocalDateTime.now(),
	            errorMessage,
	            request.getDescription(false));
	    
		return new ResponseEntity<Object>(errorDetails,HttpStatus.BAD_REQUEST);
	}	
	
}