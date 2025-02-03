package com.molesgroup.rotizeriaElNono.DTOs;

import jakarta.validation.constraints.NotNull;

public record DTOOrderDetailForPostOrder(
        @NotNull
        Integer quantity,
        String detail,

        DTOProductDetails product
) {
}
