package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.model.Customer;
import com.molesgroup.rotizeriaElNono.DTOs.DTOPostOrder;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseOrder;
import com.molesgroup.rotizeriaElNono.model.OrderDetail;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;
import com.molesgroup.rotizeriaElNono.repository.CustomerRepository;
import com.molesgroup.rotizeriaElNono.repository.OrderRepository;
import com.molesgroup.rotizeriaElNono.exception.ResourceNotFoundException;
import com.molesgroup.rotizeriaElNono.exception.UserTypeNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                         CustomerRepository customerRepository,
                         OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderDetailService = orderDetailService;
    }

    private List<DTOResponseOrder> getOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomerOrderByOrderDateDesc(customer)
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    private List<DTOResponseOrder> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    public List<DTOResponseOrder> getOrdersByUser(User user) {
        return switch (user.getUserType()) {
            case ADMIN -> getAllOrders();
            case CUSTOMER -> {
                var customer = customerRepository.findByUser(user)
                        .orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
                yield getOrdersByCustomer(customer);
            }
            default -> throw new UserTypeNotSupportedException("User type not supported: " + user.getUserType());
        };
    }

    public List<DTOResponseOrder> getOrdersByStatus(StatusOrder status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    public DTOResponseOrder getOrderByIdOrder(Long idOrder) {
        var order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new ResourceNotFoundException("Order id not found."));
        //añadir seguridad para que no se pueda acceder a todas las ordenes
        //solo si la orden fue realizado por el cliente en cuestion
        //o si el administrador es el que desea acceder a la orden.

        return new DTOResponseOrder(order);
    }

    @Transactional
    public DTOResponseOrder postOrder(User user, DTOPostOrder data) {
        var order = data.createOrder();
        var customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Customer id is not found."));
        Set<OrderDetail> orderDetailSet = orderDetailService.createOrderDetailList(data.details(), order);

        order.setCustomer(customer);
        order.setOrderDetails(orderDetailSet);


        return new DTOResponseOrder(orderRepository.save(order));
    }

}
