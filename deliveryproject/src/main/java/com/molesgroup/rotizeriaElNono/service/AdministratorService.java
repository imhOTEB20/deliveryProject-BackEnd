package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseAdministrator;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseClient;
import com.molesgroup.rotizeriaElNono.DTOs.DTOUpdateProfile;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public DTOResponseAdministrator getAdministratorByUser(User user) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found for the user."));
        return new DTOResponseAdministrator(admin);
    }

    @Transactional
    public DTOResponseClient updateAdministrator(User user, DTOUpdateProfile data) {
        var admin = this.administratorRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found for the user."));

        data.updateAdministrator(admin);

        return new DTOResponseAdministrator(admin);
    }
}
