package com.molesgroup.deliveryproject.model;

import com.molesgroup.deliveryproject.model.enums.PromotionType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "promotions")
@AttributeOverride(name = "id", column = @Column(name = "idpromotion"))
public class Promotion extends Product{

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @Column(name = "minimumamount")
    private Float minimumAmount;

    @Getter
    @Column(name = "discountpercentage")
    private Float discountPercentage;

    @OneToMany(mappedBy = "promotion")
    private Set<OrderDetail> orderDetails;

    @Getter
    @OneToMany(mappedBy = "promotion")
    private Set<Combo> combos;
}
