package com.molesgroup.deliveryproject.model;

import com.molesgroup.deliveryproject.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth0_id")
    private String auth0Id;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "usertype")
    private UserType userType;
}
