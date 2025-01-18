package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.User;
import com.molesgroup.deliveryproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUsuarioByAuth0Id(String auth0Id) {
        return userRepository.findByAuth0Id(auth0Id);
    }
}
