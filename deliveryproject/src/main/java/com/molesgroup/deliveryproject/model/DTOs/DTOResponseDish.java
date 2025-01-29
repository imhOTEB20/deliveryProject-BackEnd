package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Dish;

public record DTOResponseDish(
        Long product_cod,
        String name,
        String description,
        Float price,
        Boolean available
) implements DTOIProduct {
    public DTOResponseDish(Dish dish) {
        this(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getAvailable()
        );
    }
}
