package com.molesgroup.deliveryproject.model.DTOs;

import jakarta.validation.constraints.NotNull;

public record DTOUpdateCombo(
        Integer quantity,
        Long idDish
) {
}
