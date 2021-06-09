package com.message.processor.model;

public class Product {
    private float quantity;

    public Product(float quantity, float price) {
        this.quantity = quantity;
        this.price = price;
    }

    private float price;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}