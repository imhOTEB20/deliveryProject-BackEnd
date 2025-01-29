package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.Customer;
import com.molesgroup.deliveryproject.model.DTOs.DTOPostOrder;
import com.molesgroup.deliveryproject.model.DTOs.DTOResponseOrder;
import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.OrderDetail;
import com.molesgroup.deliveryproject.model.enums.StatusOrder;
import com.molesgroup.deliveryproject.repository.CustomerRepository;
import com.molesgroup.deliveryproject.repository.OrderDetailRepository;
import com.molesgroup.deliveryproject.repository.OrderRepository;
import com.molesgroup.deliveryproject.service.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailService orderDetailService;

    @Autowired
    private OrderService(OrderRepository orderRepository,
                         CustomerRepository customerRepository,
                         OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderDetailService = orderDetailService;
    }

    public List<DTOResponseOrder> getListOrdersForCustomer(Customer customer) {
        return orderRepository.findByCustomerOrderByOrderDateDesc(customer)
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    public List<DTOResponseOrder> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    public List<DTOResponseOrder> getOrdersByStatus(StatusOrder status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(DTOResponseOrder::new)
                .collect(Collectors.toList());
    }

    public Optional<DTOResponseOrder> getOrderByIdOrder(Long idOrder) {
        var order = orderRepository.findByIdOrder(idOrder);
        return order.map(DTOResponseOrder::new);
    }

    public Optional<Order> postOrder(DTOPostOrder data) {
        var order = data.createOrder();
        var customer = customerRepository.findByIdCustomer(data.id_customer())
                .orElseThrow(() -> new ResourceNotFoundException("Customer id is not found."));
        Set<OrderDetail> orderDetailSet = orderDetailService.createOrderDetailList(data.details(), order);

        order.setCustomer(customer);
        order.setOrderDetails(orderDetailSet);
        orderRepository.save(order);
        return Optional.of(order);
    }
}
