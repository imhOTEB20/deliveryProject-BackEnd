package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order", referencedColumnName = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "assigned_by", referencedColumnName = "id_admin")
    private Administrator assignedBy;

    @ManyToOne
    @JoinColumn(name = "delivery_assigned", referencedColumnName = "id_delivery_driver")
    private DeliveryDriver deliveryDriver;

    @Column(name = "assignment_date")
    private LocalDateTime assignmentDate;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Administrator getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Administrator assignedBy) {
        this.assignedBy = assignedBy;
    }

    public DeliveryDriver getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDriver deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public LocalDateTime getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(LocalDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
