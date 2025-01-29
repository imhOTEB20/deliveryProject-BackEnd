package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUser(User user);
    Optional<Customer> findByIdCustomer(Long idCustomer);
}
