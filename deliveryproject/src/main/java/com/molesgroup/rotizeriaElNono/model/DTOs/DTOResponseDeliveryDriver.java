package com.molesgroup.rotizeriaElNono.model.DTOs;

import com.molesgroup.rotizeriaElNono.model.DeliveryDriver;
import com.molesgroup.rotizeriaElNono.model.User;

import java.time.LocalDateTime;

public record DTOResponseDeliveryDriver(
        Long id,
        String name,
        String phone_number,
        LocalDateTime last_activity,
        String email
) implements DTOResponseClient {
    public DTOResponseDeliveryDriver(DeliveryDriver deliveryDriver, User user) {
        this(
                deliveryDriver.getId(),
                deliveryDriver.getName(),
                deliveryDriver.getPhoneNumber(),
                deliveryDriver.getLastActivity(),
                user.getEmail()
        );
    }

    public DTOResponseDeliveryDriver(DeliveryDriver deliveryDriver) {
        this(
                deliveryDriver.getId(),
                deliveryDriver.getName(),
                deliveryDriver.getPhoneNumber(),
                deliveryDriver.getLastActivity(),
                deliveryDriver.getUser().getEmail()
        );
    }
}
