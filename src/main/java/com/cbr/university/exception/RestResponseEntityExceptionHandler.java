package com.cbr.university.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        Map<String, Object> body = createBody("@Valid error. More details in the response body.", status, errors);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        Map<String, Object> body = createBody("@Validated error. More details in the response body.", status, errors);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> resourceNotFoundException(
            NoSuchElementException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map<String, Object> body = createBody("Database entry not found", status, null);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    private Map<String, Object> createBody(String exceptDescription, HttpStatusCode status, List<String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("exceptDescription", exceptDescription);
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", errors);
        return body;
    }
}
