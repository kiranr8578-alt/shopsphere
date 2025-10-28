package com.shopsphere.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
public class Cart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Each cart belongs to one user
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        // A cart can have multiple cart items
        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<CartItem> items;

        private Double totalPrice;
    }

