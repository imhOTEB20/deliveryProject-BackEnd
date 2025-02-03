package com.molesgroup.deliveryproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComboTest {

    private Combo combo;
    private Dish dish;
    private Promotion promotion;

    @BeforeEach
    void setUp() {
        // Arrange
        dish = new Dish();
        dish.setIddish(1L); // Asumiendo que hay un método setIddish en la clase Dish

        promotion = new Promotion();
        promotion.setIdpromotion(1L); // Asumiendo que hay un método setIdpromotion en la clase Promotion

        combo = new Combo();
        combo.setDish(dish);
        combo.setPromotion(promotion);
        combo.setQuantity(2);
    }

    @Test
    void testComboCreation() {
        // Act
        Long expectedDishId = combo.getDish().getIddish();
        Long expectedPromotionId = combo.getPromotion().getIdpromotion();
        Integer expectedQuantity = combo.getQuantity();

        // Assert
        assertNotNull(combo);
        assertEquals(1L, expectedDishId);
        assertEquals(1L, expectedPromotionId);
        assertEquals(2, expectedQuantity);
    }

    @Test
    void testComboSettersAndGetters() {
        // Arrange
        Combo newCombo = new Combo();
        Dish newDish = new Dish();
        newDish.setIddish(2L);
        Promotion newPromotion = new Promotion();
        newPromotion.setIdpromotion(2L);

        // Act
        newCombo.setDish(newDish);
        newCombo.setPromotion(newPromotion);
        newCombo.setQuantity(3);

        // Assert
        assertEquals(2L, newCombo.getDish().getIddish());
        assertEquals(2L, newCombo.getPromotion().getIdpromotion());
        assertEquals(3, newCombo.getQuantity());
    }

    @Test
    void testComboId() {
        // Act
        combo.setId(1L);

        // Assert
        assertEquals(1L, combo.getId());
    }
}