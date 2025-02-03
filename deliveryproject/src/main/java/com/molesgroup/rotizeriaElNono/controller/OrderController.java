package com.molesgroup.rotizeriaElNono.controller;

import com.molesgroup.rotizeriaElNono.model.DTOs.DTOPostOrder;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOResponseOrder;
import com.molesgroup.rotizeriaElNono.model.Order;
import com.molesgroup.rotizeriaElNono.model.User;
import com.molesgroup.rotizeriaElNono.model.enums.StatusOrder;
import com.molesgroup.rotizeriaElNono.service.OrderService;
import com.molesgroup.rotizeriaElNono.service.UserService;
import jakarta.validation.Valid;
import com.molesgroup.rotizeriaElNono.model.DTOs.DTOOrderDetailForPostOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(UserService userService,
                            OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {

        return ResponseEntity.ok("Order cancelled success.");
    }
}