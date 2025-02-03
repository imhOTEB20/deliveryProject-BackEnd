package com.molesgroup.rotizeriaElNono.repository;

import com.molesgroup.rotizeriaElNono.model.Administrator;
import com.molesgroup.rotizeriaElNono.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
    Optional<Administrator> findByUser(User user);
    Optional<Administrator> findById(Long id);
}
