package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Data
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "modifyby", referencedColumnName = "idadmin")
    private Administrator modifyby;

    private String name;
    private String description;
    private Boolean available;
}
