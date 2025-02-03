package com.molesgroup.rotizeriaElNono.controller;

import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseCustomer;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTORegisterCustomer;
import com.molesgroup.rotizeriaElNono.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public DTOResponseCustomer registerCustomer(@RequestBody DTORegisterCustomer data) {
        return this.customerService.registerCustomer(data);
    }

    @GetMapping
    public List<DTOResponseCustomer> getAllCustomer() {
        return this.customerService.findAll();
    }
}
