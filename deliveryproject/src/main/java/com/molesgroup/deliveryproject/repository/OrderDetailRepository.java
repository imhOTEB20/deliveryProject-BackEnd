package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
