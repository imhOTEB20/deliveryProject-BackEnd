package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Assignment;

public record DTOResponseAssignment(
        Long id,
        DTOOrderDeliveryDetail order_delivery_detail,
        Long assigned_by,
        Long id_delivery_driver
) {
    public DTOResponseAssignment(Assignment assignment) {
        this(
                assignment.getId(),
                new DTOOrderDeliveryDetail(assignment.getOrder()),
                assignment.getAssignedBy().getId(),
                assignment.getDeliveryDriver().getId()
        );
    }
}
