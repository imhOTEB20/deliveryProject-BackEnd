package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByOrderDateDesc(Customer customer);

    List<Order> findByStatus(StatusOrder status);

    Optional<Order> findByIdOrder(Long idOrder);
}
