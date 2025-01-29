package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "idcustomer"))
public class Customer extends Client{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders;

    private String address;
}
