package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "idcustomer"))
public class Customer extends Client{

    @OneToOne(mappedBy = "customer")
    private Order order;

    private String address;
}
