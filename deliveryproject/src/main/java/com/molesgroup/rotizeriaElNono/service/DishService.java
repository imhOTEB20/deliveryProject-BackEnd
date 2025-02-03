package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.model.DTOs.DTOPostDish;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseDish;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOUpdateDish;
import com.molesgroup.rotizeriaElNono.model.Dish;
import com.molesgroup.rotizeriaElNono.repository.DishRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService (DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DTOResponseDish> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(DTOResponseDish::new)
                .collect(Collectors.toList());
    }

    public Optional<DTOResponseDish> getDishForId(Long idDish) {
        var dish = dishRepository.findById(idDish);
        return dish.map(DTOResponseDish::new);
    }

    public void toggleAvailability(Long idDish) {
        var dish = dishRepository.findById(idDish);
        if(dish.isPresent()) {
            dish.get().toggleAvailability();
            dishRepository.save(dish.get());
        } else {
            throw new ResourceNotFoundException("Dish id not found.");
        }
    }

    public void updateDish(Long idDish, DTOUpdateDish data) {
        var dish = dishRepository.findById(idDish)
                .orElseThrow(() -> new ResourceNotFoundException("Dish id not found"));

        data.updateDish(dish);
        dishRepository.save(dish);
    }

    public Optional<Dish> postDish(DTOPostDish data) {
        Dish dish = new Dish();
        data.postDish(dish);

        return Optional.of(dishRepository.save(dish));
    }
}
