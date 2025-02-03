package com.molesgroup.rotizeriaElNono.controller;

import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseProducts;
import com.molesgroup.rotizeriaElNono.service.DishService;
import com.molesgroup.rotizeriaElNono.service.ProductService;
import com.molesgroup.rotizeriaElNono.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/products")
public class ProductController {

    private final DishService dishService;
    private final PromotionService promotionService;
    private final ProductService productService;

    @Autowired
    public ProductController(DishService dishService,
                             PromotionService promotionService,
                             ProductService productService) {
        this.dishService = dishService;
        this.promotionService = promotionService;
        this.productService = productService;
    }

    @GetMapping
    public DTOResponseProducts findAll() {
        return this.productService.findAll();
    }
}
