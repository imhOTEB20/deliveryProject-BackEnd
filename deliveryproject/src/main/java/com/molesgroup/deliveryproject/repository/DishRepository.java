package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByIdDish(Long idDish);
}
