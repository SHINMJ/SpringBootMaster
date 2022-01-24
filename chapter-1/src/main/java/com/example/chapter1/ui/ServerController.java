package com.example.chapter1.ui;


import com.example.chapter1.domain.Dish;
import com.example.chapter1.service.KitchenService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> serveDishes() {
        return kitchenService.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> deliverDishes() {
        return kitchenService.getDishes()
            .map(dish -> Dish.deliver(dish));
    }
}
