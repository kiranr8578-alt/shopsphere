package com.shopsphere.controller;

import com.shopsphere.entity.Order;
import com.shopsphere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}/cart/{cartId}")
    public Order createOrder(@PathVariable Long userId, @PathVariable Long cartId) {
        return orderService.createOrderFromCart(userId, cartId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateStatus(id, status);
    }
}
