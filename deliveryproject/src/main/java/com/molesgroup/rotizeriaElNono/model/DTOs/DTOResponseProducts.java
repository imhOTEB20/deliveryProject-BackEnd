package com.molesgroup.rotizeriaElNono.model.DTOs;

import java.util.List;

public record DTOResponseProducts(
        List<DTOResponseDish> dishes,
        List<DTOResponsePromotion> promotions
) {

}
