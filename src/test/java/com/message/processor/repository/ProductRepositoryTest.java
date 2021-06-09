package com.message.processor.repository;

import com.message.processor.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository = null;

    @BeforeEach
    void setUp() {
        productRepository = ProductRepository.getInstance();
        Product product1 = new Product(10,10);
        productRepository.append("a",product1);

        Product product2 = new Product(20,20);
        productRepository.append("b",product2);
        Product product3 = new Product(30,20);
        productRepository.append("a",product3);
        Product product4 = new Product(30,20);
        productRepository.append("b",product4);

        assertNotEquals(productRepository.get_size(),0);
    }

    @Test
    void getInstance() {
        ProductRepository prodRepo1 = ProductRepository.getInstance();
        ProductRepository prodRepo2 = ProductRepository.getInstance();

        assertSame(prodRepo1, prodRepo2);
    }

    @Test
    void append() {
        ProductRepository prodRepo1 = ProductRepository.getInstance();
        Product prod = new Product(10f, 20f);
        Product prod2 = new Product(5f, 25f);

        prodRepo1.append("apple", prod);
        prodRepo1.append("orange", prod2);

        assertEquals(prodRepo1.products.size(), 4);

    }

    @Test
    void contains() {
        ProductRepository prod = ProductRepository.getInstance();
        assertTrue(prod.contains("a"));
    }

    @Test
    void get() {
        ProductRepository prod = ProductRepository.getInstance();
        assertEquals(prod.get("a").getQuantity(), 30);
        assertEquals(prod.get("a").getPrice(), 20);
    }

    @Test
    void remove() {
        ProductRepository prod = ProductRepository.getInstance();
        prod.remove("a");

        assertNull(prod.get("a"));
    }

    @Test
    void get_size() {
        ProductRepository prod = ProductRepository.getInstance();

        assertEquals(prod.get_size(), 4);
    }
}