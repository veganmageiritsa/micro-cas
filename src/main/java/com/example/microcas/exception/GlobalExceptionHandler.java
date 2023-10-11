package com.example.microcas.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.microcas.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
        Exception ex,
        WebRequest webRequest) {
        var error = new ErrorResponseDto(webRequest.getDescription(false),
                                         HttpStatus.INTERNAL_SERVER_ERROR,
                                         ex.getMessage(),
                                         LocalDateTime.now());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error);
    }
    
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExists(
        CustomerAlreadyExistsException ex,
        WebRequest webRequest) {
        var error = new ErrorResponseDto(webRequest.getDescription(false),
                                         HttpStatus.BAD_REQUEST,
                                         ex.getMessage(),
                                         LocalDateTime.now());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundException(
        ResourceNotFoundException ex,
        WebRequest webRequest) {
        var error = new ErrorResponseDto(webRequest.getDescription(false),
                                         HttpStatus.NOT_FOUND,
                                         ex.getMessage(),
                                         LocalDateTime.now());
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, String> validationErrors =
            ex.getBindingResult().getAllErrors()
              .stream()
              .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                                        DefaultMessageSourceResolvable::getDefaultMessage));
        
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
    
}
