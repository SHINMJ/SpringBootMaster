package com.example.ecommercials.domain;

import java.util.Objects;
import org.springframework.data.annotation.Id;

public class Item {

    private @Id String id;
    private String name;
    private double price;

    protected Item() {
    }

    private Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Item of(String name, double price) {
        return new Item(name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
