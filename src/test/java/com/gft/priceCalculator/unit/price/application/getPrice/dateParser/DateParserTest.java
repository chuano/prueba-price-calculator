package com.gft.priceCalculator.unit.price.application.getPrice.dateParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gft.priceCalculator.price.application.getPrice.dateParser.DateParser;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateParserTest {
    @Test
    public void test_returns_parsed_date_given_valid_date_string() throws InvalidDateException {
        Date expectedDate = new Date(1592128800000L); // 2020-06-14 10:00:00 timestamp

        DateParser dateParser = new DateParser();
        Date result = dateParser.parsedDate("2020-06-14T10:00:00");

        assertEquals(result, expectedDate);
    }

    @Test
    public void test_throws_exception_given_invalid_date_string() {
        DateParser dateParser = new DateParser();

        InvalidDateException exception = assertThrows(InvalidDateException.class, () -> dateParser.parsedDate("2020-06-14T10:00"));

        assertEquals("Invalid date format: 2020-06-14T10:00" , exception.getMessage());
    }
}
