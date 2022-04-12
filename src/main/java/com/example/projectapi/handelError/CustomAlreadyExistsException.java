package com.example.projectapi.handelError;

public class CustomAlreadyExistsException extends RuntimeException {

    public CustomAlreadyExistsException(String message) {
        super(message);
    }

    public CustomAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
