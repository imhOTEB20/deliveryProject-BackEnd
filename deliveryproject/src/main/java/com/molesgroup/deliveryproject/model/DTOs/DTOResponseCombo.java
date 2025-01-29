package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Combo;

public record DTOResponseCombo(
        Long id,
        String name,
        String description,
        Integer quantity
) {
    public DTOResponseCombo(Combo combo) {
        this(
                combo.getId(),
                combo.getDish().getName(),
                combo.getDish().getDescription(),
                combo.getQuantity()
        );
    }
}
