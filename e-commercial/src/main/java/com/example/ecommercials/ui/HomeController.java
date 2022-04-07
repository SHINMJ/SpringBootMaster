package com.example.ecommercials.ui;

import com.example.ecommercials.domain.Cart;
import com.example.ecommercials.domain.CartRepository;
import com.example.ecommercials.domain.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    public HomeController(ItemRepository itemRepository,
        CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", itemRepository.findAll())
            .modelAttribute("cart", cartRepository.findById("MyCart").defaultIfEmpty(Cart.from("MyCart")))
            .build()
        );
    }
}