package com.molesgroup.rotizeriaElNono.model;

import com.molesgroup.rotizeriaElNono.model.enums.PromotionType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "promotions")
@AttributeOverride(name = "id", column = @Column(name = "id_promotion"))
public class Promotion extends Product{

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @Column(name = "minimum_amount")
    private Float minimumAmount;

    @Column(name = "discount_percentage")
    private Float discountPercentage;

    @OneToMany(mappedBy = "promotion")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Combo> combos;

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public Float getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Float minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<Combo> getCombos() {
        return combos;
    }

    public void setCombos(Set<Combo> combos) {
        this.combos = combos;
    }
}
