package com.molesgroup.rotizeriaElNono.controller;

import com.molesgroup.rotizeriaElNono.exception.UserTypeNotSupportedException;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseClient;
import com.molesgroup.rotizeriaElNono.DTOs.DTOUpdateProfile;
import com.molesgroup.rotizeriaElNono.service.AdministratorService;
import com.molesgroup.rotizeriaElNono.service.CustomerService;
import com.molesgroup.rotizeriaElNono.service.DeliveryDriverService;
import com.molesgroup.rotizeriaElNono.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final CustomerService customerService;
    private final DeliveryDriverService deliveryDriverService;
    private final AdministratorService administratorService;

    public ProfileController(UserService userService,
                             CustomerService customerService,
                             DeliveryDriverService deliveryDriverService,
                             AdministratorService administratorService) {

        this.administratorService = administratorService;
        this.userService = userService;
        this.deliveryDriverService = deliveryDriverService;
        this.customerService = customerService;
    }

    @GetMapping
    public DTOResponseClient getProfile() {
        //Buscar el usuario en la base de datos
        var user = userService.getUserByAuth0Id();

        // Devolver datos según el tipo de usuario
        return switch (user.getUserType()) {
            case ADMIN -> {
                yield administratorService.getAdministratorByUser(user);
            }
            case CUSTOMER -> {
                yield customerService.getCustomer(user);
            }
            case DELIVERY -> {
                yield deliveryDriverService.getDeliveryDriverByUser(user);
            }
            default -> throw new UserTypeNotSupportedException("User type is not supported." + user.getUserType());
        };
    }

    @PutMapping
    public DTOResponseClient updateProfile(@RequestBody DTOUpdateProfile data) {
        var user = userService.getUserByAuth0Id();

        return switch (user.getUserType()) {
            case CUSTOMER -> {
                yield customerService.updateCustomer(user, data);
            }
            case ADMIN -> {
                yield administratorService.updateAdministrator(user, data);
            }
            case DELIVERY -> {
                yield deliveryDriverService.updateDeliveryDriver(user, data);
            }
            default -> throw new UserTypeNotSupportedException("User type is not supported.");
        };
    }
}
