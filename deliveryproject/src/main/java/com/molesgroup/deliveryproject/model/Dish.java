package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
@AttributeOverride(name = "id", column = @Column(name = "iddish"))
public class Dish extends Product{

    @OneToMany(mappedBy = "dish")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "dish")
    private Set<Combo> combos;
}
