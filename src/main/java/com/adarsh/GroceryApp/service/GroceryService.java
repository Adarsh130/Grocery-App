package com.adarsh.GroceryApp.service;

import com.adarsh.GroceryApp.exception.ResourceNotFoundException;
import com.adarsh.GroceryApp.model.GroceryItem;
import com.adarsh.GroceryApp.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepository groceryRepository;

    // Fetch all items
    public List<GroceryItem> getAllItems() {
        return groceryRepository.findAll();
    }

    // Fetch single item by ID
    public GroceryItem getItemById(String id) {
        return groceryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
    }

    // Add new item
    public GroceryItem addItem(GroceryItem item) {
        return groceryRepository.save(item); // MongoDB generates ObjectId automatically
    }

    // Update existing item
    public GroceryItem updateItem(String id, GroceryItem updatedItem) {
        return groceryRepository.findById(id).map(existingItem -> {

            // Update all fields including category
            if (updatedItem.getName() != null) {
                existingItem.setName(updatedItem.getName());
            }
            if (updatedItem.getPrice() != 0) {
                existingItem.setPrice(updatedItem.getPrice());
            }
            if (updatedItem.getQuantity() != 0) {
                existingItem.setQuantity(updatedItem.getQuantity());
            }
            if (updatedItem.getCategory() != null) {
                existingItem.setCategory(updatedItem.getCategory()); // ✅ Update category
            }

            return groceryRepository.save(existingItem); // Persist changes to MongoDB
        }).orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
    }


    // Delete item by ID
    public void deleteItem(String id) {
        if (!groceryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with ID: " + id);
        }
        groceryRepository.deleteById(id);
    }
    //filter by name

    public List<GroceryItem> filterByName(String name){
        return groceryRepository.findByNameIgnoreCase((name));

    }
    //filter by price
    public List<GroceryItem> filterByPrice(double price){
        return groceryRepository.findByPriceLessThanEqual(price);
    }
    //filter by quantity
    public List<GroceryItem> filterByQuantity(int quantity){
        double price;
        return groceryRepository.findByPriceLessThanEqual(quantity);
    }
    //filter by category
    public List<GroceryItem> filterByCategory(String category){
        return groceryRepository.findByCategoryIgnoreCase(category);
    }
}
