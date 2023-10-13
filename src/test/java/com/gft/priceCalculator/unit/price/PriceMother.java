package com.gft.priceCalculator.unit.price;

import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceDateRange;
import com.gft.priceCalculator.price.domain.PriceId;
import com.gft.priceCalculator.price.domain.PriceListId;
import com.gft.priceCalculator.price.domain.PriceMoney;
import com.gft.priceCalculator.price.domain.PricePriority;
import com.gft.priceCalculator.price.domain.PriceProductId;

import java.util.Date;
import java.util.UUID;

public class PriceMother {
    public static Price random() {
        return new Price(
                new PriceId(UUID.randomUUID()),
                new PriceBrandId(1),
                new PriceProductId(35455),
                new PriceListId(1),
                new PriceDateRange(
                        new Date(),
                        new Date()
                ),
                new PricePriority(1),
                new PriceMoney(25.5, "EUR")
        );
    }
}
