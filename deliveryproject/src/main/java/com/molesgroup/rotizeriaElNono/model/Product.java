package com.molesgroup.rotizeriaElNono.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modify_by", referencedColumnName = "id_admin")
    private Administrator modifyBy;

    private String name;
    private String description;
    private Boolean available;
    private Float price;

    public void toggleAvailability() {
        this.available = !this.available;
    }

    public Long getId() {
        return id;
    }

    public Administrator getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Administrator modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
