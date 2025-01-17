package com.molesgroup.deliveryproject.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliverydrivers")
@AttributeOverride(name = "id", column = @Column(name = "iddeliverydriver"))
public class DeliveryDriver extends Client{

    @Column(name = "lastactivity")
    private LocalDateTime lastActivity;
}
