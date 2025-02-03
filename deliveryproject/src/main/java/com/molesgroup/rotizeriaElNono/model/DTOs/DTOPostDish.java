package com.molesgroup.rotizeriaElNono.model.DTOs;

import com.molesgroup.rotizeriaElNono.model.Dish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOPostDish(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        Float price
) {
        public void postDish(Dish dish) {
                if(this.name != null) dish.setName(this.name);
                if(this.description != null) dish.setDescription(this.description);
                if(this.price != null) dish.setPrice(this.price);
                dish.setAvailable(true);
        }
}
