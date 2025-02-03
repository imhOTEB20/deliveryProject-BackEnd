package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Optional<Combo> findById(Long id);
}
