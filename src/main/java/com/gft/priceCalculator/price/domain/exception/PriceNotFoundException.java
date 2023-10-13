package com.gft.priceCalculator.price.domain.exception;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException() {
        super("Price not found");
    }
}
