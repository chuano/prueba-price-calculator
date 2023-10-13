package com.gft.priceCalculator.price.infrastructure.http.getPrice;

import com.gft.priceCalculator.price.domain.Price;

public class GetPriceResponseMapper {
    public static GetPriceResponse toDto(Price price) {
        return new GetPriceResponse(price.getProductId().value(), price.getBrandId().value(),
                price.getPriceListId().value(), price.getRange().start(), price.getRange().end(),
                price.getPrice().amount(), price.getPrice().currency());
    }
}
