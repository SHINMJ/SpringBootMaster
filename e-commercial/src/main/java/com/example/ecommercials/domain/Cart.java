package com.example.ecommercials.domain;

import java.util.List;
import org.springframework.data.annotation.Id;

public class Cart {

    private @Id String id;
    private List<CartItem> cartItems;

    private Cart() {
    }

    private Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public static Cart of(String id, List<CartItem> cartItems) {
        return new Cart(id, cartItems);
    }
}
