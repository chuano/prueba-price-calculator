package com.gft.priceCalculator.price.infrastructure.persistence;

import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceDateRange;
import com.gft.priceCalculator.price.domain.PriceId;
import com.gft.priceCalculator.price.domain.PriceListId;
import com.gft.priceCalculator.price.domain.PriceMoney;
import com.gft.priceCalculator.price.domain.PricePriority;
import com.gft.priceCalculator.price.domain.PriceProductId;

public class PriceEntityMapper {
    public static Price toDomain(PriceEntity priceEntity) {
        return new Price(new PriceId(priceEntity.id), new PriceBrandId(priceEntity.brandId),
                new PriceProductId(priceEntity.productId), new PriceListId(priceEntity.priceList),
                new PriceDateRange(priceEntity.startDate, priceEntity.endDate), new PricePriority(priceEntity.priority),
                new PriceMoney(priceEntity.price, priceEntity.currency));
    }
}
