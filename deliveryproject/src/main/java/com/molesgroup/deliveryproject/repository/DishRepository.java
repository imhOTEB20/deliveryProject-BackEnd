package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByIdDish(Long idDish);
    List<Dish> findAll();
}
