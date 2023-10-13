package com.gft.priceCalculator.unit.price.domain;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gft.priceCalculator.price.domain.PriceDateRange;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class PriceDateRangeTest {
    @Test
    void test_can_instantiate_given_same_dates() {
        Date start = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis());

        PriceDateRange range = new PriceDateRange(start, end);

        assertInstanceOf(PriceDateRange.class, range);
    }

    @Test
    void test_can_instantiate_given_end_date_later_than_start_date() {
        Date start = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis() + 1000);

        PriceDateRange range = new PriceDateRange(start, end);

        assertInstanceOf(PriceDateRange.class, range);
    }

    @Test
    void test_throws_error_given_end_date_earlier_than_start_date() {
        Date start = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis() - 1000);

        assertThrows(RuntimeException.class, () -> new PriceDateRange(start, end));
    }
}
