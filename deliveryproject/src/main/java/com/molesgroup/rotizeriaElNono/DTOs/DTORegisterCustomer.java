package com.molesgroup.rotizeriaElNono.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DTORegisterCustomer(
        @NotBlank String email,
        @NotBlank String name,
        @NotBlank String phone_number,
        @NotBlank String address
) {
}
