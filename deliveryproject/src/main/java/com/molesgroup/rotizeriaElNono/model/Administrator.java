package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "administrators")
@AttributeOverride(name = "id", column = @Column(name = "id_admin"))
public class Administrator extends Consumer {

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToMany(mappedBy = "modifyBy")
    private Set<Dish> dishes;

    @OneToMany(mappedBy = "assignedBy")
    private Set<Assignment> assignments;

    public Set<Dish> getDishes() {
        return dishes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }
}
