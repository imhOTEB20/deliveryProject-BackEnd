package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.enums.PaymentMethod;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;

import java.util.List;

public record DTOPostOrder(
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
