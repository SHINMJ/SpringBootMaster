package com.example.ecommercials.operation;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;

import com.example.ecommercials.domain.Item;
import com.example.ecommercials.domain.ItemByExampleRepository;
import com.example.ecommercials.domain.ItemRepository;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final ItemByExampleRepository itemByExampleRepository;
    private final FluentMongoOperations fluentMongoOperations;

    public InventoryService(ItemRepository itemRepository1,
        ItemByExampleRepository itemRepository,
        FluentMongoOperations fluentMongoOperations) {
        this.itemRepository = itemRepository1;
        this.itemByExampleRepository = itemRepository;
        this.fluentMongoOperations = fluentMongoOperations;
    }

    public Flux<Item> findAll() {
        return itemRepository.findAll();
    }

    public Flux<Item> searchByExample(String name, String desc, boolean useAnd) {

        return itemByExampleRepository.findAll(where(name, desc, useAnd));
    }

    public List<Item> searchByFluentExample(String name, String desc, boolean useAnd) {

        return fluentMongoOperations.query(Item.class)
            .matching(Query.query(byExample(where(name, desc, useAnd))))
            .all();
    }
    
    private Example<Item> where(String name, String desc, boolean useAnd) {
        Item item = Item.of(name, desc, 0.0);
        ExampleMatcher matcher = (useAnd ?
            ExampleMatcher.matchingAll() :
            ExampleMatcher.matchingAny())
            .withStringMatcher(StringMatcher.CONTAINING)
            .withIgnoreCase()
            .withIgnorePaths("price");
        return Example.of(item, matcher);
    }
}
