package com.molesgroup.deliveryproject.model;

import com.molesgroup.deliveryproject.model.enums.PaymentMethod;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idcustomer", referencedColumnName = "idcustomer")
    private Customer customer;

    @OneToOne(mappedBy = "order")
    private Assignment assignment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @Column(name = "deliveryaddress")
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentmethod")
    private PaymentMethod paymentMethod;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;
}
