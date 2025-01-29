package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.DTOs.DTOResponseAssignment;
import com.molesgroup.deliveryproject.model.DeliveryDriver;
import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;
import com.molesgroup.deliveryproject.repository.AssignmentRepository;
import com.molesgroup.deliveryproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final OrderRepository orderRepository;

    @Autowired
    private AssignmentService(AssignmentRepository assignmentRepository, OrderRepository orderRepository) {
        this.assignmentRepository = assignmentRepository;
        this.orderRepository = orderRepository;
    }

    public List<DTOResponseAssignment> getAssignmentByDeliveryDriver(DeliveryDriver deliveryDriver) {
        return assignmentRepository.findByDeliveryDriver(deliveryDriver).
                stream().map(DTOResponseAssignment::new)
                .collect(Collectors.toList());
    }

    public List<DTOResponseAssignment> getAllAssignment() {
        return assignmentRepository.findAll().stream().map(DTOResponseAssignment::new).collect(Collectors.toList());
    }

    public List<DTOResponseAssignment> getPendingAssignment() {
        List<Order> orders = orderRepository.findByStatus(StatusOrder.ACCEPTED);
        return orders.stream()
                .map(order -> new DTOResponseAssignment(order.getAssignment()))
                .collect(Collectors.toList());
    }
}
