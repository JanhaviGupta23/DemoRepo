package com.cg.IMSOrder.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.IMSOrder.exception.IdNotFoundException;
import com.cg.IMSOrder.exception.IngredientsNotAvailable;

@RestControllerAdvice
public class OrderExceptionHandler {
	
	  @ExceptionHandler(IdNotFoundException.class)
	    public ResponseEntity<String> handleidNotFoundException(IdNotFoundException except){
	        return new ResponseEntity<String>(except.getMessage(), HttpStatus.NOT_FOUND);
	    }
	  
	  @ExceptionHandler(IngredientsNotAvailable.class)
	    public ResponseEntity<String> IngredientsNotAvailableException(IngredientsNotAvailable except){
	        return new ResponseEntity<String>(except.getMessage(), HttpStatus.NOT_FOUND);
	    }

}
