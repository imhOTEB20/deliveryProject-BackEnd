package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Optional<Combo> findByIdCombo(Long idCombo);
}
