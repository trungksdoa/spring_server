package com.example.projectapi;

import com.example.projectapi.dtos.ErrorMessage;
import com.example.projectapi.dtos.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {

        ErrorMessage message = new ErrorMessage();
        message.setErrorMessage("File too large!");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }

}