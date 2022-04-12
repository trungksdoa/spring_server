package com.example.projectapi.handelError;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandle {
    public ZonedDateTime getTime() {
        LocalDateTime myLocalDateTime = LocalDateTime.now();

//        System.out.println("My LocalDateTime: " + myLocalDateTime); // 2020-05-15T13:45:30

        // UTC+7 (Ho Chi Minh Vietnam).
        ZoneId vnZoneId = ZoneId.of("Asia/Ho_Chi_Minh");

        // Add time zone information to LocalDateTime.
        ZonedDateTime vnDateTime = ZonedDateTime.of(myLocalDateTime, vnZoneId);

        return vnDateTime;
    }

    @ExceptionHandler(value = {CustomIllegalStateException.class})
    public ResponseEntity<Object> CustomIllegalStateException(CustomIllegalStateException e) {
        HttpStatus server_error = HttpStatus.INTERNAL_SERVER_ERROR;
        Exception exception = new Exception(
                e.getMessage(),
                null,
                server_error,
                getTime());
        //Return custom exception
        return new ResponseEntity<>(exception, server_error);
    }

    @ExceptionHandler(value = {CustomNotFoundException.class})
    public ResponseEntity<Object> CustomUserNotFoundException(CustomNotFoundException e) {
        HttpStatus server_error = HttpStatus.NOT_FOUND;
        Exception exception = new Exception(
                e.getMessage(),
                null,
                server_error,
                getTime());
        //Return custom exception
        return new ResponseEntity<>(exception, server_error);
    }

    @ExceptionHandler(value = {CustomAlreadyExistsException.class})
    public ResponseEntity<Object> CustomUserNotFoundException(CustomAlreadyExistsException e) {
        HttpStatus server_error = HttpStatus.BAD_REQUEST;
        Exception exception = new Exception(
                e.getMessage(),
                null,
                server_error,
                getTime());
        //Return custom exception
        return new ResponseEntity<>(exception, server_error);
    }
}
