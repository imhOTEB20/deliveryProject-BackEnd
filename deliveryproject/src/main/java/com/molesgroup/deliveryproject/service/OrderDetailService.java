package com.molesgroup.deliveryproject.service;

import com.molesgroup.deliveryproject.model.DTOs.DTOOrderDetailForPostOrder;
import com.molesgroup.deliveryproject.model.Dish;
import com.molesgroup.deliveryproject.model.Order;
import com.molesgroup.deliveryproject.model.OrderDetail;
import com.molesgroup.deliveryproject.model.Promotion;
import com.molesgroup.deliveryproject.model.enums.ProductType;

import com.molesgroup.deliveryproject.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    @Autowired
    private OrderDetailService(OrderDetailRepository orderDetailRepository,
                               ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }

    private void chargeProduct(OrderDetail orderDetail, ProductType type, Long idProduct) {
        var product = productService.getProduct(type, idProduct);
        switch (type) {
            case DISH -> orderDetail.setDish((Dish) product);
            case PROMOTION -> orderDetail.setPromotion((Promotion) product);
        }
    }

    public Set<OrderDetail> createOrderDetailList(List<DTOOrderDetailForPostOrder> details, Order order) {
        return details.stream()
                .map(dtoOrderDetailForPostOrder -> {
                    var orderDetail = new OrderDetail();
                    this.chargeProduct(orderDetail,
                            dtoOrderDetailForPostOrder.product().type(),
                            dtoOrderDetailForPostOrder.product().id()
                    );
                    orderDetail.setQuantity(dtoOrderDetailForPostOrder.quantity());
                    orderDetail.setDetails(dtoOrderDetailForPostOrder.details());

                    orderDetail.setOrder(order);
                    return orderDetail;
                }).collect(Collectors.toSet());
    }
}
