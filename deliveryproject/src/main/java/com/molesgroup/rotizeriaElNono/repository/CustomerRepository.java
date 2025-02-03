package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUser(User user);
    Optional<Customer> findById(Long id);
}
