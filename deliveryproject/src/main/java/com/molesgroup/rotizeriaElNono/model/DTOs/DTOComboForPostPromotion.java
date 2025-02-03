package com.molesgroup.rotizeriaElNono.model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOComboForPostPromotion(
        @NotNull
        Integer quantity,
        @NotBlank
        Long idDish
) {

}
