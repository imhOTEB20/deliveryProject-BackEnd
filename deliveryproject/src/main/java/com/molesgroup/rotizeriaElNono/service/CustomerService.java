package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import com.molesgroup.rotizeriaElNono.exception.UserAlreadyExistsException;
import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseCustomer;
import com.molesgroup.rotizeriaElNono.DTOs.DTORegisterCustomer;
import com.molesgroup.rotizeriaElNono.DTOs.DTOUpdateProfile;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.repository.CustomerRepository;
import com.molesgroup.rotizeriaElNono.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           UserRepository userRepository,
                           UserService userService,
                           AuthService authService) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.authService = authService;
    }

    @Transactional
    public DTOResponseCustomer registerCustomer(DTORegisterCustomer data) {
        if (userRepository.findByAuth0Id(authService.getAuth0IdFromToken()).isPresent()) {
            throw new UserAlreadyExistsException("User already register");
        }

        var customer = new Customer();
        var user = userService.createUserTypeCustomer(data);

        customer.setUser(user);
        customer.setName(data.name());
        customer.setPhoneNumber(data.phone_number());
        customer.setAddress(data.address());

        return new DTOResponseCustomer(customerRepository.save(customer), user);
    }

    @Transactional
    public DTOResponseCustomer updateCustomer(User user, DTOUpdateProfile data) {
        var customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id not found"));

        data.updateCustomer(customer);

        return new DTOResponseCustomer(customer);
    }

    public DTOResponseCustomer getCustomer(User user) {
        var customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for the user."));
        return new DTOResponseCustomer(customer);
    }

    public List<DTOResponseCustomer> findAll() {
        return this.customerRepository.findAll()
                .stream()
                .map(DTOResponseCustomer::new)
                .collect(Collectors.toList());
    }
}
