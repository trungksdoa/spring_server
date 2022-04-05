package com.example.projectapi.handelError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandle {

    @ExceptionHandler(value =  {CustomIllegalStateException.class})
    public ResponseEntity<Object> CustomIllegalStateException(CustomIllegalStateException e) {
        HttpStatus server_error = HttpStatus.INTERNAL_SERVER_ERROR;
        Exception exception = new Exception(
                e.getMessage(),
                e,
                server_error,
                ZonedDateTime.now(ZoneId.of("Z")));
        //Return custom exception
        return new ResponseEntity<>(exception,server_error);
    }

    @ExceptionHandler(value =  {CustomUserNotFoundException.class})
    public ResponseEntity<Object> CustomUserNotFoundException(CustomUserNotFoundException e) {
        HttpStatus server_error = HttpStatus.NOT_FOUND;
        Exception exception = new Exception(
                e.getMessage(),
                e,
                server_error,
                ZonedDateTime.now(ZoneId.of("Z")));
        //Return custom exception
        return new ResponseEntity<>(exception,server_error);
    }
}
