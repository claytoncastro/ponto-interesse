package com.project.pontointeresse.exceptions;

import com.project.pontointeresse.exceptions.details.ErrorDataDetails;
import com.project.pontointeresse.exceptions.details.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        ValidationErrorDetails veDetails = ValidationErrorDetails.builder()
                .timestamp(new Date().getTime())
                .status(BAD_REQUEST.value())
                .title("Erro de Validação de campo")
                .detail("Erro no(s) campo(s)")
                .developerMessage(manvException.getClass().getSimpleName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();
        return new ResponseEntity<>(veDetails, BAD_REQUEST);
    }

}
