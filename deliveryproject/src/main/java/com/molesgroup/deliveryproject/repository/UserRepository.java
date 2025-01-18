package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAuth0Id(String auth0Id);
}
