package com.molesgroup.deliveryproject.repository;

import com.molesgroup.deliveryproject.model.Administrator;
import com.molesgroup.deliveryproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
    Administrator findByUser(User user);
    Administrator findByIdAdministrator(Long idAdministrator);
}
