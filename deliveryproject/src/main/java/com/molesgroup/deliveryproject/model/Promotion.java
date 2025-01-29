package com.molesgroup.deliveryproject.model;

import com.molesgroup.deliveryproject.model.enums.PromotionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "promotions")
@AttributeOverride(name = "id", column = @Column(name = "idpromotion"))
public class Promotion extends Product{

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private PromotionType type;

    @Getter
    @Setter
    @Column(name = "minimumamount")
    private Float minimumAmount;

    @Getter
    @Setter
    @Column(name = "discountpercentage")
    private Float discountPercentage;

    @OneToMany(mappedBy = "promotion")
    private Set<OrderDetail> orderDetails;

    @Getter
    @Setter
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Combo> combos;
}
