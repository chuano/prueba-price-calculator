package com.gft.priceCalculator.price.infrastructure.http.getPrice;

import java.util.Date;

public record GetPriceResponse(Integer productId, Integer brandId, Integer priceList, Date startDate, Date endDate,
                               Double price, String currency) {
}
