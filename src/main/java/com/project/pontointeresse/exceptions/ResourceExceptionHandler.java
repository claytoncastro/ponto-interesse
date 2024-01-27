package com.project.pontointeresse.exceptions;

import com.project.pontointeresse.exceptions.details.ParseDataDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParseDataException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(ParseDataException rnfException) {
        ParseDataDetails resourceNotFound = ParseDataDetails.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Problema ao converter dado!")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(resourceNotFound, BAD_REQUEST);
    }

}
