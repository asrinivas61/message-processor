package com.message.processor.repository;

import com.message.processor.model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    static HashMap<String, Product> products = null;

    private static ProductRepository productRepository = null;

    private ProductRepository() {}

    public static ProductRepository getInstance() {
        if (productRepository == null) {
            productRepository = new ProductRepository();
            products = new HashMap<>();
        }
        return productRepository;
    }

    public void append(String productName, Product product){
        products.put(productName, product);
    }

    public boolean contains(String productName){
        return products.containsKey(productName);
    }

    public Product get(String productName){
        return products.get(productName);
    }

    public HashMap<String, Product> view() {
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String key = entry.getKey();
            Product product = entry.getValue();
            System.out.println(key+"    |    "+product.getQuantity()+ "   |    "+product.getPrice());
        }
        return products;
    }

    public void remove(String name) {
        products.remove(name);
    }

    public int get_size(){
        return products.size();
    }
}