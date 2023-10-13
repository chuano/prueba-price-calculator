package com.gft.priceCalculator.price.application.getPrice;

import com.gft.priceCalculator.price.application.getPrice.dateParser.DateParser;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;
import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceProductId;
import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.domain.persistence.PriceRepository;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GetPriceHandler {
    private final DateParser dateParser;
    private final PriceRepository priceRepository;

    public GetPriceHandler(DateParser dateParser, PriceRepository priceRepository) {
        this.dateParser = dateParser;
        this.priceRepository = priceRepository;
    }

    public Price handle(GetPriceQuery query) throws PriceNotFoundException, InvalidDateException {
        PriceBrandId brandId = new PriceBrandId(query.brandId());
        PriceProductId productId = new PriceProductId(query.productId());
        Date date = dateParser.parsedDate(query.date());

        return priceRepository.findPrice(brandId, productId, date);
    }
}
