package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.OrderDetail;

public record DTOOrderDetail (
        DTOIProduct product,
        Integer quantity,
        String details
) {
    public DTOOrderDetail (OrderDetail orderDetail) {
        this(
                (orderDetail.getPromotion() == null) ? new DTOResponseDish(orderDetail.getDish())
                        : new DTOResponsePromotion(orderDetail.getPromotion()),
                orderDetail.getQuantity(),
                orderDetail.getDetails()
        );
    }
}
