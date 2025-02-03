package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.DeliveryDriver;
import com.molesgroup.rotizeriaElNono.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
    Optional<DeliveryDriver> findByUser(User user);
    Optional<DeliveryDriver> findById(Long id);
}
