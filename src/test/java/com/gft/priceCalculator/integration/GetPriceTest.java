package com.gft.priceCalculator.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPriceTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("provideCorrectData")
    public void test_get_price_value(
            int brandId, int productId, int productList, String startDate, String endDate,
            String date, double price, String currency
    ) throws Exception {
        String url = "/api/v1/price?brandId=" + brandId + "&productId=" + productId + "&date=" + date;
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(productList))
                .andExpect(jsonPath("$.startDate").value(startDate))
                .andExpect(jsonPath("$.endDate").value(endDate))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.currency").value(currency));
    }

    private static Stream<Arguments> provideCorrectData() {
        return Stream.of(
                Arguments.of(1, 35455, 1, "2020-06-14T00:00:00.000+00:00", "2020-12-31T23:59:59.000+00:00", "2020-06-14T10:00:00", 35.50, "EUR"),
                Arguments.of(1, 35455, 2, "2020-06-14T15:00:00.000+00:00", "2020-06-14T18:30:00.000+00:00", "2020-06-14T16:00:00", 25.45, "EUR"),
                Arguments.of(1, 35455, 1, "2020-06-14T00:00:00.000+00:00", "2020-12-31T23:59:59.000+00:00", "2020-06-14T21:00:00", 35.50, "EUR"),
                Arguments.of(1, 35455, 3, "2020-06-15T00:00:00.000+00:00", "2020-06-15T11:00:00.000+00:00", "2020-06-15T10:00:00", 30.50, "EUR"),
                Arguments.of(1, 35455, 4, "2020-06-15T16:00:00.000+00:00", "2020-12-31T23:59:59.000+00:00", "2020-06-16T21:00:00", 38.95, "EUR")
        );
    }

    @Test
    public void test_returns_error_given_parameters_with_no_results() throws Exception {
        String url = "/api/v1/price?brandId=2&productId=35455&date=2020-06-16T21:00:00";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_returns_error_given_invalid_date() throws Exception {
        String url = "/api/v1/price?brandId=2&productId=35455&date=202006-16T21:00:00";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_returns_error_given_missing_parameter() throws Exception {
        String url = "/api/v1/price?productId=35455&date=2020-06-16T21:00:00";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isBadRequest());
    }
}
