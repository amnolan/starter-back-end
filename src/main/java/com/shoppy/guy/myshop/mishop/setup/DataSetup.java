package com.shoppy.guy.myshop.mishop.setup;

import com.shoppy.guy.myshop.mishop.model.ShoppingItem;
import com.shoppy.guy.myshop.mishop.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataSetup implements ApplicationRunner {

    private ShoppingItemRepository repository;

    @Autowired
    public DataSetup(ShoppingItemRepository shoppingItemRepository){
        this.repository = shoppingItemRepository;
    }

    public void run(ApplicationArguments args){
        Optional<ShoppingItem> shoppingItemStart = repository.findById(1);
        if (!shoppingItemStart.isPresent()){
            ShoppingItem shoppingItem = ShoppingItem.builder()
                    .price("100")
                    .itemDescription("A delicious sweet treat made with chalk, sugar and imitation chocolate compounds.")
                    .itemName("Chocolate Bar")
                    .build();
            repository.save(shoppingItem);
            ShoppingItem shoppingItem1 = ShoppingItem.builder()
                    .price("50")
                    .itemDescription("Sour and made with diamond dust, inedible but looks great")
                    .itemName("Sowrpash kydz")
                    .build();
            repository.save(shoppingItem1);
        }
    }

}
