package com.shopsphere.service;

import com.shopsphere.entity.Cart;
import com.shopsphere.entity.Order;
import com.shopsphere.entity.OrderItem;
import com.shopsphere.entity.User;
import com.shopsphere.repository.CartRepository;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private  CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrderFromCart(Long userId, Long cartId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(cartItem.getProduct());
            oi.setQuantity(cartItem.getQuantity());
            oi.setPrice(cartItem.getPrice());
            return oi;
        }).toList();

        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalPrice(total);
        order.setItems(new HashSet<>(orderItems));

        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.getItems().size(); // initialize
        return order;
    }

    // To be checked we need to find all orders placed by particular user
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAll();
    }

    public void updateStatus(Long id, String status) {
        Order order = getOrder(id);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
