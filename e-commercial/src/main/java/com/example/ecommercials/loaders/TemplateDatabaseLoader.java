package com.example.ecommercials.loaders;

import com.example.ecommercials.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    CommandLineRunner initialize(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.save(Item.of("Alarm clock", 19.99));
            mongoOperations.save(Item.of("Smart TV tray", 24.99));
        };
    }
}
