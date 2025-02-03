package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseAssignment;
import com.molesgroup.rotizeriaElNono.model.DeliveryDriver;
import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;
import com.molesgroup.rotizeriaElNono.repository.AssignmentRepository;
import com.molesgroup.rotizeriaElNono.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, OrderRepository orderRepository) {
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
