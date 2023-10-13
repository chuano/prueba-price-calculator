package com.gft.priceCalculator.price.infrastructure.http.getPrice;

import static com.gft.priceCalculator.price.application.getPrice.dateParser.DateParser.DATE_FORMAT;

import com.gft.priceCalculator.price.application.getPrice.GetPriceHandler;
import com.gft.priceCalculator.price.application.getPrice.GetPriceQuery;
import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class GetPriceController {
    @Autowired
    private GetPriceHandler handler;

    @GetMapping("/api/v1/price")
    public ResponseEntity<GetPriceResponse> execute(
            @RequestParam @Parameter(description = "Brand identifier") Integer brandId,
            @RequestParam @Parameter(description = "Product identifier") Integer productId,
            @RequestParam @Parameter(description = "UTC DateTime ISO format \"" + DATE_FORMAT + "\"") String date
    ) throws PriceNotFoundException, InvalidDateException {
        GetPriceQuery query = new GetPriceQuery(brandId, productId, date);
        Price price = handler.handle(query);

        return ResponseEntity.ok(GetPriceResponseMapper.toDto(price));
    }
}
