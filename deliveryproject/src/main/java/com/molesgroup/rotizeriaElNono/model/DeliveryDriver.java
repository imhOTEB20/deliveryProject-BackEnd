package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "delivery_drivers")
@AttributeOverride(name = "id", column = @Column(name = "id_delivery_driver"))
public class DeliveryDriver extends Consumer {

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @Column(name = "lastactivity")
    private LocalDateTime lastActivity;

    @OneToMany(mappedBy = "deliveryDriver")
    private Set<Assignment> assignments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }
}
