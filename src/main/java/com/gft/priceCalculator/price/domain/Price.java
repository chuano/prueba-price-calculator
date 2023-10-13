package com.gft.priceCalculator.price.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Price {
    private PriceId id;
    private PriceBrandId brandId;
    private PriceProductId productId;
    private PriceListId priceListId;
    private PriceDateRange range;
    private PricePriority priority;
    private PriceMoney price;
}
