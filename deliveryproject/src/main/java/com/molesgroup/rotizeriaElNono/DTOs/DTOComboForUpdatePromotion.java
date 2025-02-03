package com.molesgroup.rotizeriaElNono.DTOs;

import jakarta.validation.constraints.NotNull;

public record DTOComboForUpdatePromotion(
        @NotNull
        Long idCombo,
        @NotNull
        Integer quantity,
        Long idDish
) {

}
