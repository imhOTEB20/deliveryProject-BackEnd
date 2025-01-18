package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "deliverydrivers")
@AttributeOverride(name = "id", column = @Column(name = "iddeliverydriver"))
public class DeliveryDriver extends Client{

    @Column(name = "lastactivity")
    private LocalDateTime lastActivity;

    @OneToMany(mappedBy = "deliveryDriver")
    private Set<Assignment> assignments;
}
