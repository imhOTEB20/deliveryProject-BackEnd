package com.molesgroup.rotizeriaElNono.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOComboForPostPromotion(
        @NotNull
        Integer quantity,
        @NotBlank
        Long idDish
) {

}
