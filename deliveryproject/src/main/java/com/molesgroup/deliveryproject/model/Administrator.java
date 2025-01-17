package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrators")
@AttributeOverride(name = "id", column = @Column(name = "idadmin"))
public class Administrator extends Client{

    @OneToOne(mappedBy = "modifyby")
    private Dish dish;
}
