package com.example.ecommercials.ui;

import com.example.ecommercials.domain.Cart;
import com.example.ecommercials.domain.CartRepository;
import com.example.ecommercials.domain.ItemRepository;
import com.example.ecommercials.operation.CartService;
import com.example.ecommercials.operation.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    private static final String CART_ID = "MyCart";

    private final CartService cartService;
    private final InventoryService inventoryService;


    public HomeController(ItemRepository itemRepository,
        CartRepository cartRepository, CartService cartService,
        InventoryService inventoryService) {
        this.cartService = cartService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", inventoryService.findAll())
            .modelAttribute("cart", cartService.findById(CART_ID))
            .build()
        );
    }

    @GetMapping("/search")
    public Mono<Rendering> search(@RequestParam(required = false) String name,
        @RequestParam(required = false) String desc, @RequestParam boolean useAnd) {
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("results", inventoryService.searchByExample(name, desc, useAnd))
            .build()
        );
    }

    @PostMapping("/add/{id}")
    public Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart(CART_ID, id)
            .thenReturn("redirect:/");
    }
}
