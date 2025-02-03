package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.enums.PaymentMethod;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;

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
