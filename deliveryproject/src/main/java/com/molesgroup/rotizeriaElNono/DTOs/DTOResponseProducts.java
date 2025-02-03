package com.molesgroup.rotizeriaElNono.DTOs;

import java.util.List;

public record DTOResponseProducts(
        List<DTOResponseDish> dishes,
        List<DTOResponsePromotion> promotions
) {

}
