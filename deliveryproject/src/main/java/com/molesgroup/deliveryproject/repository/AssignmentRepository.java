package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Assignment;
import com.molesgroup.deliveryproject.model.DeliveryDriver;
import com.molesgroup.deliveryproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByOrder(Order order);
    List<Assignment> findByDeliveryDriver(DeliveryDriver deliveryDriver);
}
