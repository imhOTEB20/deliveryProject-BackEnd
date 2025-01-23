package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.DTOs.DTOResponseOrder;
import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<DTOResponseOrder> getListOrdersForCustomer(Customer customer) {
        return orderRepository.findByCustomerOrderByOrderDateDesc(customer).
                stream().
                map(DTOResponseOrder::new).
                collect(Collectors.toList());
    }

    public Optional<DTOResponseOrder> getOrderByIdOrder(Long idOrder) {
        var order = orderRepository.findByIdOrder(idOrder);
        return order.map(DTOResponseOrder::new);
    }
}
