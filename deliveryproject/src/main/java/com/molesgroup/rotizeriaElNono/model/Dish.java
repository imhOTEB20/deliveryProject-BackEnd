package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dishes")
@AttributeOverride(name = "id", column = @Column(name = "id_dish"))
public class Dish extends Product{

    @OneToMany(mappedBy = "dish")
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "dish")
    private Set<Combo> combos;

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
