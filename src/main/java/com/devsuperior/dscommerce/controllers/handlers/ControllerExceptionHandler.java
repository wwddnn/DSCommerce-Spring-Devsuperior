package com.devsuperior.dscommerce.controllers.handlers;
import com.devsuperior.dscommerce.dto.CustomError;
import com.devsuperior.dscommerce.dto.ValidationError;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

//global treatments for exceptions
//we used handlers inside class controllers, so we don't need to use  try catch block  in controller class.
@ControllerAdvice
public class ControllerExceptionHandler {

    //this method intercept exception ResourceNotFoundException
    //NOT_FOUND is 404.
    // status.value() go pass numeric to integer.
    //request.getRequestURI() go handle to path of URI.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //BAD_REQUEST is 400
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    // UNPROCESSABLE_ENTITY is 422
    //getFieldErrors() cycle through all errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
            for(FieldError f : e.getBindingResult().getFieldErrors()) {
                err.addError(f.getField(), f.getDefaultMessage());
            }
            return ResponseEntity.status(status).body(err);
    }
}
