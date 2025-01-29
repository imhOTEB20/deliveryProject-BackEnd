package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.enums.PaymentMethod;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;

import java.time.LocalDateTime;

public record DTOOrderDeliveryDetail(
        String deliveryAddress,
        StatusOrder status,
        PaymentMethod paymentMethod,
        LocalDateTime orderDate
) {
    public DTOOrderDeliveryDetail (Order order) {
        this(
                order.getDeliveryAddress(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getOrderDate()
        );
    }
}
