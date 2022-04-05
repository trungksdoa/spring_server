package com.example.projectapi.handelError;

public class CustomUserNotFoundException extends RuntimeException {

    public CustomUserNotFoundException(String message) {
        super(message);
    }
    public CustomUserNotFoundException(String message, Throwable throwable) {
        super(message,throwable);
    }

}
