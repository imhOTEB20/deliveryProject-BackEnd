package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Data
public abstract class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private User user;

    private String name;
}
