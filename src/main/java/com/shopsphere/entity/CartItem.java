package com.shopsphere.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each item belongs to a cart
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("items")
    private Cart cart;

    // Product associated with this cart item
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private Double price; // price = product.price * quantity

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CartItem)) return false;
//        CartItem that = (CartItem) o;
//        return Objects.equals(cart, that.cart) &&
//                Objects.equals(product, that.product);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cart, product);
//    }
}
