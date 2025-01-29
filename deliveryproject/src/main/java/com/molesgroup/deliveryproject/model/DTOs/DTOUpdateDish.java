package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Dish;

public record DTOUpdateDish(
        String name,
        String description,
        Float price
) {
        public void updateDish(Dish dish) {
                if(this.name != null) dish.setName(this.name);
                if(this.description != null) dish.setDescription(this.description);
                if(this.price != null) dish.setPrice(this.price);
        }
}
