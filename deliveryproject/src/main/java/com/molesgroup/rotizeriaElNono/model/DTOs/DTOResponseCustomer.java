package com.molesgroup.rotizeriaElNono.model.DTOs;

import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.model.User;

public record DTOResponseCustomer(
        Long id,
        String name,
        String phone_number,
        String address,
        String email
) implements DTOResponseClient {
    public DTOResponseCustomer(Customer customer, User user) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                user.getEmail()
        );
    }

    public DTOResponseCustomer(Customer customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getUser().getEmail()
        );
    }
}
