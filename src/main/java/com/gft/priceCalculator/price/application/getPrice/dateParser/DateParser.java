package com.gft.priceCalculator.price.application.getPrice.dateParser;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class DateParser {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public Date parsedDate(String date) throws InvalidDateException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

            return formatter.parse(date);
        } catch (ParseException exception) {
            throw new InvalidDateException("Invalid date format: " + date);
        }
    }
}
