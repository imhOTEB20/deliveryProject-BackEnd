package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findByIdPromotion(Long idPromotion);
}
