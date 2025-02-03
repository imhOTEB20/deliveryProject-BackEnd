package com.molesgroup.rotizeriaElNono.service;

import com.molesgroup.rotizeriaElNono.DTOs.DTOOrderDetailForPostOrder;
import com.molesgroup.rotizeriaElNono.model.Dish;
import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.OrderDetail;
import com.molesgroup.rotizeriaElNono.model.Promotion;
import com.molesgroup.rotizeriaElNono.model.enums.ProductType;

import com.molesgroup.rotizeriaElNono.repository.OrderDetailRepository;
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
    public OrderDetailService(OrderDetailRepository orderDetailRepository,
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
                    orderDetail.setDetails(dtoOrderDetailForPostOrder.detail());

                    orderDetail.setOrder(order);
                    return orderDetail;
                }).collect(Collectors.toSet());
    }
}
