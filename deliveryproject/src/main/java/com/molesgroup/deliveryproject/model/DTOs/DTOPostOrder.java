package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.enums.PaymentMethod;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;

import java.util.List;

public record DTOPostOrder(
        Long id_customer,
        List<DTOOrderDetailForPostOrder> details,
        String deliveryAddress,
        PaymentMethod paymentMethod
) {
    public Order createOrder() {
        var order = new Order();
        order.setDeliveryAddress(this.deliveryAddress);
        order.setPaymentMethod(this.paymentMethod);
        order.setStatus(StatusOrder.PENDING);
        return order;
    }
}
