package com.gft.priceCalculator.price.domain.persistence;

import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceProductId;
import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;

import java.util.Date;

public interface PriceRepository {
    Price findPrice(PriceBrandId brandId, PriceProductId productId, Date date) throws PriceNotFoundException;
}
