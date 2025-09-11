package com.adarsh.GroceryApp.model;

import org.springframework.data.annotation.Id;
import com.adarsh.GroceryApp.service.GroceryService;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "grocery_items")
public class GroceryItem {

    @Id

    private String id;
    private String name;
    private double price;
    private int quantity;
    public String category;
    public GroceryItem(){}
    public GroceryItem(String id, String name, double price, int quantity,String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category= category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
