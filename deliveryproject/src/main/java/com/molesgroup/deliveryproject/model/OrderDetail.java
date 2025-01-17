package com.molesgroup.deliveryproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "orderdetails")
public class OrderDetail {
    @Id
    @Column(name = "idorderdetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idorder")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "iddish")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "idpromotion")
    private Promotion promotion;

    private Integer quantity;
    private String details;
}
