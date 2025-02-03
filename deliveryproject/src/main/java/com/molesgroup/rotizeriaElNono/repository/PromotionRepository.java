package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Optional<Promotion> findById(Long id);
}
