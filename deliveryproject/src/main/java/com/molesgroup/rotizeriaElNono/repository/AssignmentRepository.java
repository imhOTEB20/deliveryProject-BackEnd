package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Assignment;
import com.molesgroup.rotizeriaElNono.model.DeliveryDriver;
import com.molesgroup.rotizeriaElNono.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Assignment findByOrder(Order order);
    List<Assignment> findByDeliveryDriver(DeliveryDriver deliveryDriver);
}
