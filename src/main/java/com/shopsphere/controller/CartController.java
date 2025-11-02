package com.shopsphere.controller;

import com.shopsphere.entity.Cart;
import com.shopsphere.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/carts")
@RestController
public class CartController {

    @Autowired
    private  CartService cartService;

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping("/user/{userId}")
    public Cart createCart(@PathVariable Long userId) {
        return cartService.createCart(userId);
    }

    @PostMapping("/{cartId}/add")
    public Cart addItem(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.addItem(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    public Cart removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        return cartService.removeItem(cartId, itemId);
    }
}
