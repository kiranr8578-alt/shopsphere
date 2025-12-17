package com.shopsphere.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Each cart belongs to one user
        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonIgnoreProperties({"carts", "orders"})
        private User user;

        // A cart can have multiple cart items
        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnoreProperties("cart")
        private List<CartItem> items;

        private Double totalPrice;
    }

