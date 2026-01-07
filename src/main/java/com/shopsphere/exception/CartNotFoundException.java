package com.shopsphere.exception;

import lombok.Data;

@Data
public class CartNotFoundException extends RuntimeException {

  private Long cartID;
    public CartNotFoundException(String message, Long cartId) {
        super(message);
        this.cartID = cartId;
    }



}
