package com.message.processor.service;

import com.message.processor.model.Product;
import com.message.processor.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void processTxtFile() {
        MessageService.processFile();
    }

    @Test
    void processTxtFileTest() {
        MessageService.processFile();
        ProductRepository prod = ProductRepository.getInstance();

        assertEquals(prod.get("apple").getPrice(), 300);
        assertEquals(prod.get("orange").getPrice(), 415);

        assertEquals(prod.get("apple").getQuantity(), 30);
        assertEquals(prod.get("orange").getQuantity(), 41);

    }
}