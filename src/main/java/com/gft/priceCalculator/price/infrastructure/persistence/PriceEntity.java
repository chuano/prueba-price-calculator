package com.gft.priceCalculator.price.infrastructure.persistence;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prices")
public class PriceEntity {
    @Id
    @Column
    public UUID id;

    @Column
    public Integer brandId;

    @Column
    public Date startDate;

    @Column
    public Date endDate;

    @Column
    public Integer productId;

    @Column
    public Integer priceList;

    @Column
    public Double price;

    @Column
    public String currency;

    @Column
    public Integer priority;
}
