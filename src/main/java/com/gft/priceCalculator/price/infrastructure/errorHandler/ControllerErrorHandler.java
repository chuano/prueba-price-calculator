package com.gft.priceCalculator.price.infrastructure.errorHandler;

import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerErrorHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNotFoundException(PriceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleMissingParameterException(MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(new ErrorMessage("Missing required parameter " + exception.getParameterName()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleTypeException(MethodArgumentTypeMismatchException exception) {
        return new ResponseEntity<>(new ErrorMessage("Invalid " + exception.getPropertyName() + " value: " + exception.getValue()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleInvalidDate(InvalidDateException exception) {
        return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleGeneralException(Throwable exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorMessage("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
