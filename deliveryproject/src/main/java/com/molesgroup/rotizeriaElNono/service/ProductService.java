package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseDish;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseProducts;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponsePromotion;
import com.molesgroup.rotizeriaElNono.model.Product;
import com.molesgroup.rotizeriaElNono.model.enums.ProductType;
import com.molesgroup.rotizeriaElNono.repository.DishRepository;
import com.molesgroup.rotizeriaElNono.repository.PromotionRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductService {

    private final PromotionRepository promotionRepository;
    private final DishRepository dishRepository;

    @Autowired
    public ProductService(PromotionRepository promotionRepository,
                           DishRepository dishRepository) {
        this.promotionRepository = promotionRepository;
        this.dishRepository = dishRepository;
    }

    @SuppressWarnings("unchecked")
    public <T extends Product> T getProduct(ProductType type, Long idProduct) {
        if (type == null) {
            throw new IllegalArgumentException("Product type must not be null.");
        }

        return switch (type) {
            case DISH -> (T) dishRepository.findById(idProduct)
                    .orElseThrow(() -> new ResourceNotFoundException("Dish id not found."));
            case PROMOTION -> (T) promotionRepository.findById(idProduct)
                    .orElseThrow(() -> new ResourceNotFoundException("Promotion id not found."));
        };
    }

    public DTOResponseProducts findAll() {
        return new DTOResponseProducts(
                dishRepository.findAll()
                        .stream()
                        .map(DTOResponseDish::new)
                        .collect(Collectors.toList()),
                promotionRepository.findAll()
                        .stream()
                        .map(DTOResponsePromotion::new)
                        .collect(Collectors.toList()));
    }
}
