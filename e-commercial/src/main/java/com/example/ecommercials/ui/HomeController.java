package com.example.ecommercials.ui;

import com.example.ecommercials.domain.Cart;
import com.example.ecommercials.domain.CartRepository;
import com.example.ecommercials.domain.ItemRepository;
import com.example.ecommercials.operation.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private static final String CART_ID = "MyCart";

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;


    public HomeController(ItemRepository itemRepository,
        CartRepository cartRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @GetMapping
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", itemRepository.findAll())
            .modelAttribute("cart", cartRepository.findById(CART_ID).defaultIfEmpty(Cart.from(CART_ID)))
            .build()
        );
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart(CART_ID, id)
            .thenReturn("redirect:/");
    }
}
