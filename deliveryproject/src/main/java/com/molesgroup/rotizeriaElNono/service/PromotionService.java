package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.model.Combo;
import com.molesgroup.rotizeriaElNono.model.DTOs.*;
import com.molesgroup.rotizeriaElNono.model.Promotion;
import com.molesgroup.rotizeriaElNono.model.enums.PromotionType;
import com.molesgroup.rotizeriaElNono.repository.ComboRepository;
import com.molesgroup.rotizeriaElNono.repository.DishRepository;
import com.molesgroup.rotizeriaElNono.repository.PromotionRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PromotionService(PromotionRepository promotionRepository,
                             ComboRepository comboRepository,
                             DishRepository dishRepository,
                             ComboService comboService) {
        this.promotionRepository = promotionRepository;
        this.dishRepository = dishRepository;
        this.comboService = comboService;
    }

    public Optional<DTOResponsePromotion> getDishForId(Long idPromotion) {
        var promotion = promotionRepository.findById(idPromotion);
        return promotion.map(DTOResponsePromotion::new);
    }

    @Transactional
    public void toggleAvailability(Long idPromotion) {
        var promotion = promotionRepository.findById(idPromotion);
        if(promotion.isPresent()) {
            promotion.get().toggleAvailability();
            promotionRepository.save(promotion.get());
        } else {
            throw new ResourceNotFoundException("Promotion id not found.");
        }
    }

    public void updatePromotion(Long idPromotion, DTOUpdatePromotion data) {
        var promotion = promotionRepository.findById(idPromotion)
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
                        var dish = dishRepository.findById(ComboDetails.idDish());
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
