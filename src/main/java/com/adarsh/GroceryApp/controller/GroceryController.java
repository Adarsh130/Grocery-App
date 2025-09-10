package com.adarsh.GroceryApp.controller;


import com.adarsh.GroceryApp.model.GroceryItem;
import com.adarsh.GroceryApp.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/grocery")
public class GroceryController {
    @Autowired
    private GroceryService groceryService;

    //fetch all items
    @GetMapping
    public List<GroceryItem>getAllItems(){
        return groceryService.getAllItems();
    }
    //fetch item by id

    @GetMapping("/{id}")
    public GroceryItem getItemById(@PathVariable String id) {
        return groceryService.getItemById(id);
    }

    // post Add new item
    @PostMapping()
    public GroceryItem addItem(@RequestBody GroceryItem item) {
        return groceryService.addItem(item);
    }

    // put Update item
    @PutMapping("/{id}")
    public GroceryItem updateItem(@PathVariable String id, @RequestBody GroceryItem updatedItem) {
        return groceryService.updateItem(id, updatedItem);
    }

    // delete Remove item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable String id) {
        groceryService.deleteItem(id);
        return "Item deleted successfully!";
    }

}
