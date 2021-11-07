package com.example.restservice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class TodoNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ExceptionResponse todoNotFoundHandler(TodoNotFoundException ex) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND ,ex.getMessage());
    }
}
