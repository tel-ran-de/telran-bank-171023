package de.telran.bank;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> exceptionHandle(ConstraintViolationException exception) {
        return ResponseEntity

                .status(HttpStatus.BAD_REQUEST).build();
    }
}
