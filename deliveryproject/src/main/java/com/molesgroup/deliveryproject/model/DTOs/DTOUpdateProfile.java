package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Customer;

public record DTOUpdateCustomer(
        String name,
        String email,
        String phone_number,
        String address
) {
    public void updateCustomer(Customer customer) {
        if(this.name != null) customer.setName(this.name);
        if(this.phone_number != null) customer.setPhoneNumber(this.phone_number);
        if(this.address != null) customer.setAddress(this.address);
        if(this.email != null) customer.getUser().setEmail(this.email);
    }
}
