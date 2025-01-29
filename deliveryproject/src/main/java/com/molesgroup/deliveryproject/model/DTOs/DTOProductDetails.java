package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOProductDetails(
        @NotBlank
        ProductType type,
        @NotNull
        Long id
) {
}
