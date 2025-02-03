package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Promotion;
import com.molesgroup.rotizeriaElNono.model.enums.PromotionType;

import java.util.List;

public record DTOUpdatePromotion(
        PromotionType type,
        String name,
        String description,
        Float price,
        Float minimumAmount,
        Float discountPercentage,
        List<DTOComboForUpdatePromotion> details
) {
    public void updatePromotion(Promotion promotion) {
        if (this.name != null) promotion.setName(this.name);
        if (this.description != null) promotion.setDescription(this.description);
        if (this.type == PromotionType.COMBO) {
            if (this.price != null) promotion.setPrice(this.price);
        } else if (this.type == PromotionType.DISCOUNT) {
            if (this.minimumAmount != null) promotion.setMinimumAmount(this.minimumAmount);
            if (this.discountPercentage != null) promotion.setDiscountPercentage(this.discountPercentage);
        }
    }
}
