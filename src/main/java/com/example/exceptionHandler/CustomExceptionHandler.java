package com.example.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.error.response.ErrorResponse;
import com.example.exception.UserNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        //List<String> details = new ArrayList<>();
        //details.add(ex.getLocalizedMessage());
		Map<String, String> details=new HashMap<>();
		details.put("description",ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Unsuccessful",details);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
}
