package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.Product;
import com.molesgroup.deliveryproject.model.enums.ProductType;
import com.molesgroup.deliveryproject.repository.DishRepository;
import com.molesgroup.deliveryproject.repository.PromotionRepository;
import com.molesgroup.deliveryproject.service.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final PromotionRepository promotionRepository;
    private final DishRepository dishRepository;

    @Autowired
    private ProductService(PromotionRepository promotionRepository,
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
            case DISH -> (T) dishRepository.findByIdDish(idProduct)
                    .orElseThrow(() -> new ResourceNotFoundException("Dish id not found."));
            case PROMOTION -> (T) promotionRepository.findByIdPromotion(idProduct)
                    .orElseThrow(() -> new ResourceNotFoundException("Promotion id not found."));
        };
    }

}
