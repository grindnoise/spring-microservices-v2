package com.microservices.loans.exception;

import com.microservices.loans.dto.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.HtmlUtils;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        final var msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(ErrorResponseDto.of(
                HttpStatus.BAD_REQUEST,
                msg,
                HtmlUtils.htmlEscape(request.getDescription(false))),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.of(
                HttpStatus.BAD_REQUEST,
                ex.getConstraintViolations().stream().map(violation -> violation.getPropertyPath() + " " + violation.getMessage()).collect(Collectors.joining(", ")),
                HtmlUtils.htmlEscape(request.getDescription(false))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                HtmlUtils.htmlEscape(request.getDescription(false))), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.of(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                HtmlUtils.htmlEscape(request.getDescription(false))), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(LoanAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(ErrorResponseDto.of(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                HtmlUtils.htmlEscape(request.getDescription(false))), HttpStatus.BAD_REQUEST);
    }

}
