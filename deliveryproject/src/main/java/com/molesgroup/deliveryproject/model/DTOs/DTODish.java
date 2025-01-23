package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Dish;

public record DTODish (
        String name,
        String description,
        Float price
) implements DTOIProduct {
    public DTODish (Dish dish) {
        this(
                dish.getName(),
                dish.getDescription(),
                dish.getPrice()
        );
    }
}
