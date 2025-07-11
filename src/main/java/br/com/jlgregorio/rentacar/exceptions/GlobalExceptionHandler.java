package br.com.jlgregorio.rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomExceptionResponse> handleGenericExceptions(Exception e, WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), e.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<CustomExceptionResponse> handleResourceNotFoundException(Exception e, WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(new Date(), e.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
