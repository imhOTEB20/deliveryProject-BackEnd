package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.DTOs.DTORegisterCustomer;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.model.enums.UserType;
import com.molesgroup.rotizeriaElNono.repository.UserRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository,
                       AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public User getUserByAuth0Id() {
        String auth0Id = authService.getAuth0IdFromToken();
        return userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    protected User createUserTypeCustomer(DTORegisterCustomer data) {
        var user = new User();
        var auth0 = authService.getAuth0IdFromToken();
        user.setAuth0Id(auth0);
        user.setEmail(data.email());
        user.setUserType(UserType.CUSTOMER);
        return userRepository.save(user);
    }


}

