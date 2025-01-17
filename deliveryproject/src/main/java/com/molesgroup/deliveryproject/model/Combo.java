package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcombo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iddish", referencedColumnName = "iddish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "idpromotion", referencedColumnName = "idpromotion")
    private Promotion promotion;

    private Integer quantity;
}
