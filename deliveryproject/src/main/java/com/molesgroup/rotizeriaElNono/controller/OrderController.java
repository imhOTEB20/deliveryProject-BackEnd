package com.molesgroup.rotizeriaElNono.controller;

import com.molesgroup.rotizeriaElNono.DTOs.DTOPostOrder;
import com.molesgroup.rotizeriaElNono.DTOs.DTOResponseOrder;
import com.molesgroup.rotizeriaElNono.service.OrderService;
import com.molesgroup.rotizeriaElNono.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<DTOResponseOrder> getForUser() {
        var user = this.userService.getUserByAuth0Id();
        return orderService.getOrdersByUser(user);
    }

    @PostMapping
    public DTOResponseOrder postOrder(@RequestBody DTOPostOrder data) {
        var user = this.userService.getUserByAuth0Id();
        return this.orderService.postOrder(user, data);
    }
}