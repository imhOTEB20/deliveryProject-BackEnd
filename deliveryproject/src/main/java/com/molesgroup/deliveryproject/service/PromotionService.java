package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.Combo;
import com.molesgroup.deliveryproject.model.DTOs.*;
import com.molesgroup.deliveryproject.model.Promotion;
import com.molesgroup.deliveryproject.model.enums.PromotionType;
import com.molesgroup.deliveryproject.repository.ComboRepository;
import com.molesgroup.deliveryproject.repository.DishRepository;
import com.molesgroup.deliveryproject.repository.PromotionRepository;
import com.molesgroup.deliveryproject.service.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final DishRepository dishRepository;

    private final ComboService comboService;

    @Autowired
    private PromotionService(PromotionRepository promotionRepository,
                             ComboRepository comboRepository,
                             DishRepository dishRepository,
                             ComboService comboService) {
        this.promotionRepository = promotionRepository;
        this.dishRepository = dishRepository;
        this.comboService = comboService;
    }

    public List<DTOResponsePromotion> getAllDishes() {
        return promotionRepository.findAll()
                .stream()
                .map(DTOResponsePromotion::new)
                .collect(Collectors.toList());
    }

    public Optional<DTOResponsePromotion> getDishForId(Long idPromotion) {
        var promotion = promotionRepository.findByIdPromotion(idPromotion);
        return promotion.map(DTOResponsePromotion::new);
    }

    public void toggleAvailability(Long idPromotion) {
        var promotion = promotionRepository.findByIdPromotion(idPromotion);
        if(promotion.isPresent()) {
            promotion.get().toggleAvailability();
            promotionRepository.save(promotion.get());
        } else {
            throw new ResourceNotFoundException("Promotion id not found.");
        }
    }

    public void updatePromotion(Long idPromotion, DTOUpdatePromotion data) {
        var promotion = promotionRepository.findByIdPromotion(idPromotion)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion id not found"));

        data.updatePromotion(promotion);

        Set<Combo> newCombos = data.details()
                .stream()
                .map(comboDetail -> {
                    if(comboDetail.idDish() != null) {
                        return comboService.createNewCombo(comboDetail, promotion);
                    } else {
                        return comboService.updateQuantity(comboDetail.idCombo(), comboDetail.quantity());
                    }
                }).collect(Collectors.toSet());

        promotion.setCombos(newCombos);
    }

    public Optional<Promotion> postPromotion(DTOPostPromotion data) {
        Promotion promotion = new Promotion();
        data.postPromotion(promotion);

        if (data.type() == PromotionType.COMBO) {
            Set<Combo> newCombos = data.details()
                    .stream()
                    .map(ComboDetails -> {
                        var dish = dishRepository.findByIdDish(ComboDetails.idDish());
                        Combo newCombo = new Combo();
                        newCombo.setQuantity(ComboDetails.quantity());
                        newCombo.setDish(dish.orElseThrow(() -> new ResourceNotFoundException("Dish id not found")));
                        newCombo.setPromotion(promotion);
                        return newCombo;
                    }).collect(Collectors.toSet());

            promotion.setCombos(newCombos);
        }

        return Optional.of(promotionRepository.save(promotion));
    }
}
