package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Promotion;
import com.molesgroup.deliveryproject.model.enums.PromotionType;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DTOPostPromotion(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        PromotionType type,
        Float price,
        Float minimumAmount,
        Float discountPercentage,
        List<DTOComboForPostPromotion> details
) {
    public void postPromotion(Promotion promotion) {
        promotion.setName(this.name);
        promotion.setDescription(this.description);
        promotion.setType(this.type);
        if (this.type == PromotionType.COMBO) {
            promotion.setPrice(this.price);
        } else if (this.type == PromotionType.DISCOUNT) {
            promotion.setMinimumAmount(this.minimumAmount);
            promotion.setDiscountPercentage(this.discountPercentage);
        }
    }
}
