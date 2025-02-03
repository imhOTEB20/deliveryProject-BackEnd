package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "customers")
@AttributeOverride(name = "id", column = @Column(name = "id_customer"))
public class Customer extends Consumer {

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders;

    private String address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
