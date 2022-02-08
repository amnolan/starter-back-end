package com.shoppy.guy.myshop.mishop.controller;

import com.shoppy.guy.myshop.mishop.model.ShoppingItem;
import com.shoppy.guy.myshop.mishop.repository.ShoppingItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class ShoppingItemController {

    private final ShoppingItemRepository repository;

    ShoppingItemController(ShoppingItemRepository repository){
        this.repository = repository;
    }

    @GetMapping("/shoppingitem/{id}")
    public Optional<ShoppingItem> getShoppingItemById(@PathVariable("id") int id){
        return repository.findById(id);
    }

    @GetMapping("/shoppingitem/")
    public Iterable<ShoppingItem> getShoppingItems(){
        return repository.findAll();
    }

    @DeleteMapping("/shoppingitem/{id}")
    public void deleteShoppingItemById(@PathVariable("id") int id){
        repository.deleteById(id);
    }

    @PutMapping("/shoppingitem/")
    public ShoppingItem addShoppingItem(@RequestBody ShoppingItem shoppingItem){
        return repository.save(shoppingItem);
    }
}
