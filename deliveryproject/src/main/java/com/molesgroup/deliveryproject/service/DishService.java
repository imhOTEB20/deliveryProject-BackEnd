package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.DTOs.DTOPostDish;
import com.molesgroup.deliveryproject.model.DTOs.DTOResponseDish;
import com.molesgroup.deliveryproject.model.DTOs.DTOUpdateDish;
import com.molesgroup.deliveryproject.model.Dish;
import com.molesgroup.deliveryproject.repository.DishRepository;
import com.molesgroup.deliveryproject.service.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    private DishService (DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DTOResponseDish> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(DTOResponseDish::new)
                .collect(Collectors.toList());
    }

    public Optional<DTOResponseDish> getDishForId(Long idDish) {
        var dish = dishRepository.findByIdDish(idDish);
        return dish.map(DTOResponseDish::new);
    }

    public void toggleAvailability(Long idDish) {
        var dish = dishRepository.findByIdDish(idDish);
        if(dish.isPresent()) {
            dish.get().toggleAvailability();
            dishRepository.save(dish.get());
        } else {
            throw new ResourceNotFoundException("Dish id not found.");
        }
    }

    public void updateDish(Long idDish, DTOUpdateDish data) {
        var dish = dishRepository.findByIdDish(idDish)
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
