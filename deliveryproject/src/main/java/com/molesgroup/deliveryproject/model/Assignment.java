package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idassignment")
    private Long id;

    @OneToOne
    @JoinColumn(name = "idorder", referencedColumnName = "idorder")
    private Order order;

    @OneToOne
    @JoinColumn(name = "assignedby", referencedColumnName = "idadmin")
    private Administrator admin;

    @OneToOne
    @JoinColumn(name = "iddeliverydriver", referencedColumnName = "iddeliverydriver")
    private DeliveryDriver deliveryDriver;
}
