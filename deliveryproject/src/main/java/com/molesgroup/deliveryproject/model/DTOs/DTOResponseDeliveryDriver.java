package com.molesgroup.deliveryproject.model.DTOs;

import com.molesgroup.deliveryproject.model.Administrator;
import com.molesgroup.deliveryproject.model.User;

public record DTOResponseAdministrator(
        Long id,
        String name,
        String phone_number,
        String email
) implements DTOResponseClient {
    public DTOResponseAdministrator(Administrator administrator, User user) {
        this(
                administrator.getId(),
                administrator.getName(),
                administrator.getPhoneNumber(),
                user.getEmail()
        );
    }

    public DTOResponseAdministrator(Administrator administrator) {
        this(
                administrator.getId(),
                administrator.getName(),
                administrator.getPhoneNumber(),
                administrator.getUser().getEmail()
        );
    }
}
