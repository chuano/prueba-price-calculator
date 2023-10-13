package com.gft.priceCalculator.unit.price.infrastructure.errorHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.infrastructure.errorHandler.ControllerErrorHandler;
import com.gft.priceCalculator.price.infrastructure.errorHandler.ErrorMessage;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ExtendWith(MockitoExtension.class)
public class ControllerErrorHandlerTest {
    @InjectMocks
    private ControllerErrorHandler controllerErrorHandler;

    @Test
    void test_handle_price_not_found_exception() {
        PriceNotFoundException exception = new PriceNotFoundException();

        ResponseEntity<ErrorMessage> response = controllerErrorHandler.handleNotFoundException(exception);

        assertEquals("Price not found" , response.getBody().message());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void test_handle_bad_date_format_exception() {
        InvalidDateException exception = new InvalidDateException("Invalid date format: 2023-01-01");

        ResponseEntity<ErrorMessage> response = controllerErrorHandler.handleInvalidDate(exception);

        assertEquals("Invalid date format: 2023-01-01" , response.getBody().message());
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void test_handle_bad_integer_format_exception() {
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("INVALID_VALUE" ,
                Integer.class, "brandId" , null, null);

        ResponseEntity<ErrorMessage> response = controllerErrorHandler.handleTypeException(exception);

        assertEquals("Invalid brandId value: INVALID_VALUE" , response.getBody().message());
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void test_handle_missing_parameter_exception() {
        MissingServletRequestParameterException exception = new MissingServletRequestParameterException("brandId", "Integer");

        ResponseEntity<ErrorMessage> response = controllerErrorHandler.handleMissingParameterException(exception);

        assertEquals("Missing required parameter brandId" , response.getBody().message());
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void test_handle_unexpected_exceptions() {
        Exception exception = new Exception();

        ResponseEntity<ErrorMessage> response = controllerErrorHandler.handleGeneralException(exception);

        assertEquals("Internal server error" , response.getBody().message());
        assertEquals(500, response.getStatusCode().value());
    }
}
