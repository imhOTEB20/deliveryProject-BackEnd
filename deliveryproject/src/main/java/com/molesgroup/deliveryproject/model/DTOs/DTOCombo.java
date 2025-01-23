package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Combo;

public record DTOCombo(
        String name,
        String description,
        Integer quantity
) {
    public DTOCombo(Combo combo) {
        this(
                combo.getDish().getName(),
                combo.getDish().getDescription(),
                combo.getQuantity()
        );
    }
}
