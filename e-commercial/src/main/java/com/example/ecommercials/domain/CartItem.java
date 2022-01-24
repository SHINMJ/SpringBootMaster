package com.example.ecommercials.domain;

import java.util.Objects;

public class CartItem {

    private Item item;
    private int quantity;

    private CartItem() {
    }

    private CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public static CartItem from(Item item) {
        return new CartItem(item);
    }

}
