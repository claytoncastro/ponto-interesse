package com.project.pontointeresse.exceptions;

import com.project.pontointeresse.exceptions.details.ErrorDataDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParseDataException.class)
    public ResponseEntity<Object> handlerParseDataException(ParseDataException pdException) {
        ErrorDataDetails resourceNotFound = ErrorDataDetails.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Problema ao converter dado!")
                .detail(pdException.getMessage())
                .developerMessage("Classe '" + pdException.getStackTrace()[0].getFileName() + "'")
                .build();
        return new ResponseEntity<>(resourceNotFound, BAD_REQUEST);
    }

    @ExceptionHandler(IOCsvException.class)
    public ResponseEntity<Object> handlerIOCsvException(IOCsvException icException) {
        ErrorDataDetails resourceNotFound = ErrorDataDetails.builder()
                .timestamp(new Date().getTime())
                .status(INTERNAL_SERVER_ERROR.value())
                .title("Problema ao processar arquivo CSV!")
                .detail(icException.getMessage())
                .developerMessage("Classe '" + icException.getStackTrace()[0].getFileName() + "'")
                .build();
        return new ResponseEntity<>(resourceNotFound, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileIsEmptyException.class)
    public ResponseEntity<Object> handlerFileIsEmptyException(FileIsEmptyException feException) {
        ErrorDataDetails resourceNotFound = ErrorDataDetails.builder()
                .timestamp(new Date().getTime())
                .status(NOT_ACCEPTABLE.value())
                .title("Arquivo vazio ou inexistente!")
                .detail(feException.getMessage())
                .developerMessage("Classe '" + feException.getStackTrace()[0].getFileName() + "'")
                .build();
        return new ResponseEntity<>(resourceNotFound, NOT_ACCEPTABLE);
    }

}
