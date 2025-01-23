package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.enums.PaymentMethod;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;

public record DTOResponseOrder(
        Long id,
        Long id_customer,
        Long cod_assignment,
        List<DTOOrderDetail> order_details,
        String delivery_address,
        PaymentMethod payment_method,
        StatusOrder status,
        LocalDateTime order_date
) {
    public DTOResponseOrder(Order order) {
        this(
                order.getId(),
                order.getCustomer().getId(),
                order.getAssignment().getId(),
                order.getOrderDetails().stream().map(DTOOrderDetail::new).collect(Collectors.toList()),
                order.getDeliveryAddress(),
                order.getPaymentMethod(),
                order.getStatus(),
                order.getOrderDate()
        );
    }
}
