package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.DeliveryDriver;
import com.molesgroup.deliveryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {
    DeliveryDriver findByUser(User user);
    DeliveryDriver findByIdDeliveryDriver(Long idDeliveryDriver);
}
