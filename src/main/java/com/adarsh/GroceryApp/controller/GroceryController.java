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

    // Fetch all items
    @GetMapping
    public List<GroceryItem> getAllItems() {
        return groceryService.getAllItems();
    }

    // Fetch item by ID
    @GetMapping("/{id}")
    public GroceryItem getItemById(@PathVariable String id) {
        return groceryService.getItemById(id);
    }

    // Add a new item
    @PostMapping
    public GroceryItem addItem(@RequestBody GroceryItem item) {
        return groceryService.addItem(item);
    }

    // Update an item
    @PutMapping("/{id}")
    public GroceryItem updateItem(@PathVariable String id, @RequestBody GroceryItem updatedItem) {
        return groceryService.updateItem(id, updatedItem);
    }

    // Delete an item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable String id) {
        groceryService.deleteItem(id);
        return "Item deleted successfully!";
    }

    // Filter endpoint (supports name, price, quantity, category)
    @GetMapping("/filter")
    public List<GroceryItem> filterItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String category) {

        if (name != null) {
            return groceryService.filterByName(name);
        } else if (price != null) {
            return groceryService.filterByPrice(price);
        } else if (quantity != null) {
            return groceryService.filterByQuantity(quantity);
        } else if (category != null) {
            return groceryService.filterByCategory(category); // Category filter
        } else {
            throw new RuntimeException("Please provide at least one filter parameter: name, price, quantity, or category");
        }
    }
}
