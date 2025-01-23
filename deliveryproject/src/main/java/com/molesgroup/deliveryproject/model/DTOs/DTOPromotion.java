package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Promotion;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

public record DTOPromotion(
        String name,
        String description,
        Optional<Float> price,
        Optional<Float> discount_percentage,
        Optional<List<DTOCombo>> promoted_products
) implements DTOIProduct{
    public DTOPromotion(Promotion promotion) {
        this(
                promotion.getName(),
                promotion.getDescription(),
                Optional.ofNullable(promotion.getPrice()),
                Optional.ofNullable(promotion.getDiscountPercentage()),
                Optional.of(promotion.getCombos().stream().map(DTOCombo::new).collect(Collectors.toList()))
        );
    }
}
