package com.adarsh.GroceryApp.repository;


import com.adarsh.GroceryApp.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends MongoRepository<GroceryItem, String>{
    //filter by name
    List<GroceryItem> findByNameIgnoreCase(String name);

    //filter by price
    List<GroceryItem> findByPriceLessThanEqual(double price);

    //filter by price range
    List<GroceryItem> findByPriceBetween(double minPrice, double maxPrice);
    //filter by quantity
    List<GroceryItem> findByQuantityGreaterThanEqual(int quantity);
}
