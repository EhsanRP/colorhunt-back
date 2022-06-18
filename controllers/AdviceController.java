package ir.equadesign.colorhunt.controllers;

import ir.equadesign.colorhunt.exceptions.BadParameterException;
import ir.equadesign.colorhunt.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadParameterException.class)
    public ResponseEntity badParameterException(BadParameterException badParameterException) {
        return new ResponseEntity<>(badParameterException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class,HttpMessageNotReadableException.class})
    public ResponseEntity methodArgumentNotValidException() {
        return new ResponseEntity<>("Request Arguments Are Not Valid", HttpStatus.BAD_REQUEST);
    }
}
