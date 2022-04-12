package com.example.projectapi.handelError;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
