package com.example.restservice.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    HttpStatus errorCode;
    String errorMessage;

    public ExceptionResponse() {}
    
    public ExceptionResponse(HttpStatus errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
