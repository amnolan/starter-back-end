package com.shoppy.guy.myshop.mishop.repository;

import com.shoppy.guy.myshop.mishop.model.ShoppingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingItemRepository extends CrudRepository<ShoppingItem, Integer> {
}
