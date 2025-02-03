package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerOrderByOrderDateDesc(Customer customer);

    List<Order> findByStatus(StatusOrder status);

    Optional<Order> findById(Long id);
}
