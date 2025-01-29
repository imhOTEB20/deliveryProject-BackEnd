package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.Combo;
import com.molesgroup.deliveryproject.model.DTOs.DTOComboForUpdatePromotion;
import com.molesgroup.deliveryproject.model.DTOs.DTOUpdateCombo;
import com.molesgroup.deliveryproject.model.Promotion;
import com.molesgroup.deliveryproject.repository.ComboRepository;
import com.molesgroup.deliveryproject.repository.DishRepository;
import com.molesgroup.deliveryproject.service.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboService {

    private final ComboRepository comboRepository;
    private final DishRepository dishRepository;

    @Autowired
    private ComboService (ComboRepository comboRepository,
                          DishRepository dishRepository) {
        this.comboRepository = comboRepository;
        this.dishRepository = dishRepository;
    }

    public Combo updateQuantity(Long idCombo, Integer quantity) {
        var combo = comboRepository.findByIdCombo(idCombo)
                .orElseThrow(() -> new ResourceNotFoundException("Combo id not found."));

        combo.setQuantity(quantity);
        return combo;
    }

    public Combo createNewCombo(DTOComboForUpdatePromotion comboDetail, Promotion promotion) {
        var dish = dishRepository.findByIdDish(comboDetail.idDish())
                .orElseThrow(() -> new ResourceNotFoundException("Dish id not Found"));

        Combo newCombo = new Combo();
        newCombo.setDish(dish);
        newCombo.setQuantity(comboDetail.quantity());
        newCombo.setPromotion(promotion);

        return newCombo;
    }
}
