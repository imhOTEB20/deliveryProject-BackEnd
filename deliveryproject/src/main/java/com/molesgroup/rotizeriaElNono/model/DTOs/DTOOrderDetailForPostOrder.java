package com.molesgroup.rotizeriaElNono.model.DTOs;

import jakarta.validation.constraints.NotNull;

public record DTOOrderDetailForPostOrder(
        @NotNull
        Integer quantity,
        String details,

        DTOProductDetails product
) {
}
