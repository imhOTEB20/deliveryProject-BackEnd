package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.User;

public record DTOPosCreateCustomer(
        Long id,
        String name,
        String phone_number,
        String email
) {
    public DTOPosCreateCustomer(Customer customer, User user) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                user.getEmail()
        );
    }
}
