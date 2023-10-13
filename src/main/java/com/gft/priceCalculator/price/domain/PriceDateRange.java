package com.gft.priceCalculator.price.domain;

import java.util.Date;

public record PriceDateRange(Date start, Date end) {
    public PriceDateRange {
        if (start.after(end)) {
            throw new RuntimeException("Start date must be before end date");
        }
    }
}
