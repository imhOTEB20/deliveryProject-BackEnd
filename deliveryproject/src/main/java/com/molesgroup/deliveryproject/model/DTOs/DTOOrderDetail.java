package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.OrderDetail;

import java.util.Optional;

public record DTOOrderDetail (
        DTOIProduct product,
        Integer quantity,
        String details
) {
    public DTOOrderDetail (OrderDetail orderDetail) {
        this(
                (orderDetail.getPromotion() == null) ? new DTODish(orderDetail.getDish())
                        : new DTOPromotion(orderDetail.getPromotion()),
                orderDetail.getQuantity(),
                orderDetail.getDetails()
        );
    }
}
