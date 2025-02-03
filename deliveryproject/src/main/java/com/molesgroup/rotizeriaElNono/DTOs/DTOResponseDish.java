package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Dish;

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
