package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByUser(User user);
    Customer findByIdCustomer(Long idCustomer);
}
