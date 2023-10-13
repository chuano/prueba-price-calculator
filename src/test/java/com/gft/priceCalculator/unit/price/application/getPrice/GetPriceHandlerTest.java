package com.gft.priceCalculator.unit.price.application.getPrice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gft.priceCalculator.price.application.getPrice.GetPriceHandler;
import com.gft.priceCalculator.price.application.getPrice.GetPriceQuery;
import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceProductId;
import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.domain.persistence.PriceRepository;
import com.gft.priceCalculator.price.application.getPrice.dateParser.DateParser;
import com.gft.priceCalculator.price.application.getPrice.dateParser.InvalidDateException;
import com.gft.priceCalculator.unit.price.PriceMother;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class GetPriceHandlerTest {
    @Mock
    private DateParser dateParser;

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetPriceHandler handler;

    @Test
    public void test_handler_calls_repository_properly_and_returns_response() throws PriceNotFoundException, InvalidDateException {
        Integer brandId = 1;
        Integer productId = 35455;
        Date date = new Date();
        Price price = PriceMother.random();
        GetPriceQuery query = new GetPriceQuery(brandId, productId, date.toString());

        when(dateParser.parsedDate(date.toString())).thenReturn(date);
        when(priceRepository.findPrice(any(), any(), any())).thenReturn(price);
        Price result = handler.handle(query);

        verifyFindPriceParameters(brandId, productId, date);
        assertEquals(price, result);
    }

    private void verifyFindPriceParameters(Integer brandId, Integer productId, Date date) throws PriceNotFoundException {
        ArgumentCaptor<PriceBrandId> captorBrandId = ArgumentCaptor.forClass(PriceBrandId.class);
        ArgumentCaptor<PriceProductId> captorProductId = ArgumentCaptor.forClass(PriceProductId.class);
        ArgumentCaptor<Date> captorDate = ArgumentCaptor.forClass(Date.class);

        verify(priceRepository).findPrice(captorBrandId.capture(), captorProductId.capture(), captorDate.capture());

        assertEquals(brandId, captorBrandId.getValue().value());
        assertEquals(productId, captorProductId.getValue().value());
        assertEquals(date, captorDate.getValue());
    }

    @Test
    public void test_throws_price_not_found_exception_when_not_exists_price_for_criteria()
            throws PriceNotFoundException, InvalidDateException {
        Integer brandId = 1;
        Integer productId = 35455;
        Date date = new Date();
        GetPriceQuery query = new GetPriceQuery(brandId, productId, date.toString());

        when(dateParser.parsedDate(date.toString())).thenReturn(date);
        when(priceRepository.findPrice(any(), any(), any())).thenThrow(new PriceNotFoundException());

        assertThrows(PriceNotFoundException.class, () -> handler.handle(query));
    }
}
