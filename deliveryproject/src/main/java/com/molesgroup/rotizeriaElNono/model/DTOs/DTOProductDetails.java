package com.molesgroup.rotizeriaElNono.model.DTOs;

import com.molesgroup.rotizeriaElNono.model.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOProductDetails(
        @NotBlank
        ProductType type,
        @NotNull
        Long id
) {
}
