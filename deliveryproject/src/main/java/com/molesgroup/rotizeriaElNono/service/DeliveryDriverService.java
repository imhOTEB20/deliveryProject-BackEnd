package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseClient;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseDeliveryDriver;
import com.molesgroup.rotizeriaElNono.DTOs.DTOUpdateProfile;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.repository.DeliveryDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryDriverService {

    private final DeliveryDriverRepository deliveryDriverRepository;

    @Autowired
    public DeliveryDriverService(DeliveryDriverRepository deliveryDriverRepository) {
        this.deliveryDriverRepository = deliveryDriverRepository;
    }

    public DTOResponseDeliveryDriver getDeliveryDriverByUser(User user) {
        var deliveryDriver = this.deliveryDriverRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Delevery Driver not found for the user."));

        return new DTOResponseDeliveryDriver(deliveryDriver);
    }

    @Transactional
    public DTOResponseClient updateDeliveryDriver(User user, DTOUpdateProfile data) {
        var delivery = this.deliveryDriverRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Driver not found for the user."));

        data.updateDeliveryDriver(delivery);

        return new DTOResponseDeliveryDriver(delivery);
    }
}
