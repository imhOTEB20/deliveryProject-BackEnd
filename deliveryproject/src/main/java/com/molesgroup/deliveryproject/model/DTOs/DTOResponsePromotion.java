package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Promotion;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

public record DTOResponsePromotion(
        Long product_cod,
        String name,
        String description,
        Optional<Float> price,
        Optional<Float> discount_percentage,
        Optional<List<DTOResponseCombo>> promoted_products
) implements DTOIProduct{
    public DTOResponsePromotion(Promotion promotion) {
        this(
                promotion.getId(),
                promotion.getName(),
                promotion.getDescription(),
                Optional.ofNullable(promotion.getPrice()),
                Optional.ofNullable(promotion.getDiscountPercentage()),
                Optional.of(promotion.getCombos().stream().map(DTOResponseCombo::new).collect(Collectors.toList()))
        );
    }
}
