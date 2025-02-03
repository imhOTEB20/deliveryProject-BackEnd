package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findById(Long id);
}
