package com.shopsphere.service;

import com.shopsphere.entity.Cart;
import com.shopsphere.entity.CartItem;
import com.shopsphere.entity.Product;
import com.shopsphere.entity.User;
import com.shopsphere.exception.CartNotFoundException;
import com.shopsphere.repository.CartRepository;
import com.shopsphere.repository.ProductRepository;
import com.shopsphere.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@Slf4j
@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private  UserRepository userRepository;


    public Cart getCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("No Cart found  wih id ",cartId));
        log.info("Cart Not found");
        cart.getItems().size(); // initialize lazy list
        return cart;
    }

    public Cart createCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = new Cart();
        cart.setUser(user);
//        cart.setItems(new HashSet<>());
        cart.setItems(new ArrayList<>());
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    public Cart addItem(Long cartId, Long productId, int quantity) {
        Cart cart = getCart(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setPrice(existingItem.getProduct().getPrice() * existingItem.getQuantity());
        } else {
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPrice(product.getPrice() * quantity);
            item.setCart(cart);
           cart.getItems().add(item);
        }

        updateTotal(cart);
        return cartRepository.save(cart);
    }

    public Cart removeItem(Long cartId, Long itemId) {
        Cart cart = getCart(cartId);
        cart.getItems().removeIf(i -> i.getId().equals(itemId));
        updateTotal(cart);
        return cartRepository.save(cart);
    }

    private void updateTotal(Cart cart) {
        double total = cart.getItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalPrice(total);
    }
}
