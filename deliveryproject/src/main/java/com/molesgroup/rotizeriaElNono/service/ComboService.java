package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.model.Combo;
import com.molesgroup.rotizeriaElNono.DTOs.DTOComboForUpdatePromotion;
import com.molesgroup.rotizeriaElNono.model.Promotion;
import com.molesgroup.rotizeriaElNono.repository.ComboRepository;
import com.molesgroup.rotizeriaElNono.repository.DishRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboService {

    private final ComboRepository comboRepository;
    private final DishRepository dishRepository;

    @Autowired
    public ComboService (ComboRepository comboRepository,
                          DishRepository dishRepository) {
        this.comboRepository = comboRepository;
        this.dishRepository = dishRepository;
    }

    public Combo updateQuantity(Long idCombo, Integer quantity) {
        var combo = comboRepository.findById(idCombo)
                .orElseThrow(() -> new ResourceNotFoundException("Combo id not found."));

        combo.setQuantity(quantity);
        return combo;
    }

    public Combo createNewCombo(DTOComboForUpdatePromotion comboDetail, Promotion promotion) {
        var dish = dishRepository.findById(comboDetail.idDish())
                .orElseThrow(() -> new ResourceNotFoundException("Dish id not Found"));

        Combo newCombo = new Combo();
        newCombo.setDish(dish);
        newCombo.setQuantity(comboDetail.quantity());
        newCombo.setPromotion(promotion);

        return newCombo;
    }
}
