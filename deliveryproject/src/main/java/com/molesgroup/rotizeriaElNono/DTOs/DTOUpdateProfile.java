package com.molesgroup.rotizeriaElNono.DTOs;

import com.molesgroup.rotizeriaElNono.model.Administrator;
import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.model.DeliveryDriver;

public record DTOUpdateProfile(
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

    public void updateAdministrator(Administrator administrator) {
        if(this.name != null) administrator.setName(this.name);
        if(this.phone_number != null) administrator.setPhoneNumber(this.phone_number);
        if(this.email != null) administrator.getUser().setEmail(this.email);
    }

    public void updateDeliveryDriver(DeliveryDriver deliveryDriver) {
        if(this.name != null) deliveryDriver.setName(this.name);
        if(this.phone_number != null) deliveryDriver.setPhoneNumber(this.phone_number);
        if(this.email != null) deliveryDriver.getUser().setEmail(this.email);
    }
}
