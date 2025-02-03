package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.enums.PaymentMethod;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;

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
                (order.getAssignment() != null) ? order.getAssignment().getId() : null,
                order.getOrderDetails().stream().map(DTOOrderDetail::new).collect(Collectors.toList()),
                order.getDeliveryAddress(),
                order.getPaymentMethod(),
                order.getStatus(),
                LocalDateTime.now()
        );
    }
}
