package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

@Entity
@Table(name = "combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish", referencedColumnName = "id_dish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "promotion", referencedColumnName = "id_promotion")
    private Promotion promotion;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
