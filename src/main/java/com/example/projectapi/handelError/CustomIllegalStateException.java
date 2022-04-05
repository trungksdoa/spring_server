package com.example.projectapi.handelError;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;


public class CustomIllegalStateException extends RuntimeException {

    public CustomIllegalStateException(String message) {
        super(message);
    }
    public CustomIllegalStateException(String message, Throwable throwable) {
        super(message,throwable);
    }

}