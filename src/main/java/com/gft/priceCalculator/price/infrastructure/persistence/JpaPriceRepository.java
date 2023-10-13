package com.gft.priceCalculator.price.infrastructure.persistence;

import com.gft.priceCalculator.price.domain.Price;
import com.gft.priceCalculator.price.domain.PriceBrandId;
import com.gft.priceCalculator.price.domain.PriceProductId;
import com.gft.priceCalculator.price.domain.exception.PriceNotFoundException;
import com.gft.priceCalculator.price.domain.persistence.PriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;

@Repository
public class JpaPriceRepository implements PriceRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Price findPrice(PriceBrandId brandId, PriceProductId productId, Date date) throws PriceNotFoundException {
        try {
            TypedQuery<PriceEntity> query = entityManager.createQuery(
                    "SELECT p FROM PriceEntity p " +
                            "WHERE p.brandId = :brandId " +
                            "AND p.productId = :productId " +
                            "AND p.startDate <= :date " +
                            "AND p.endDate >= :date " +
                            "ORDER BY p.priority DESC " +
                            "LIMIT 1" ,
                    PriceEntity.class
            );

            PriceEntity result = query
                    .setParameter("brandId" , brandId.value())
                    .setParameter("productId" , productId.value())
                    .setParameter("date" , date, TemporalType.TIMESTAMP)
                    .getSingleResult();

            return PriceEntityMapper.toDomain(result);
        } catch (NoResultException | NonUniqueResultException e) {
            throw new PriceNotFoundException();
        }
    }
}
