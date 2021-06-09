package com.message.processor.repository;

import com.message.processor.model.Message;
import com.message.processor.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {

    @BeforeEach
    void setUp() {
        MessageRepository instance1 = MessageRepository.getInstance();
        instance1.messages = null;
    }

    @Test
    void getInstance() {

        MessageRepository instance1 = MessageRepository.getInstance();
        MessageRepository instance2 = MessageRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void addMessage() {
        MessageRepository msgInstance = MessageRepository.getInstance();
        Message msg = new Message();
        msgInstance.messages = new ArrayList<>();
        msg.setProductType("apple");
        msg.setSales(10);
        msg.setValue(30.00f);
        msg.setOperation("add");

        List<Message> liMsg = new ArrayList<>();
        liMsg.add(msg);
        msgInstance.addMessage(msg);

        assertEquals(liMsg, msgInstance.messages);
    }

    @Test
    void get_size() {
        MessageRepository msgInstance = MessageRepository.getInstance();
        msgInstance.messages = new ArrayList<>();
        Message msg = new Message();
        msg.setProductType("apple");
        msg.setSales(10);
        msg.setValue(30.00f);
        msg.setOperation("add");

        List<Message> liMsg = new ArrayList<>();
        liMsg.add(msg);
        msgInstance.addMessage(msg);

        assertEquals(liMsg.size(), msgInstance.get_size());
    }

    @Test
    void getTypeIIIMessages() {
        MessageRepository msgInstance = MessageRepository.getInstance();
        Message msg = new Message();
        msgInstance.messages = new ArrayList<>();

        msg.setProductType("apple");
        msg.setSales(30);
        msg.setValue(10f);
        msgInstance.addMessage(msg);
        Message msg1 = new Message();
        msg1.setProductType("orange");
        msg1.setSales(100);
        msg1.setValue(10.00f);
        msgInstance.addMessage(msg1);
        Message msg2 = new Message();
        msg2.setProductType("Orange");
        msg2.setSales(10);
        msg2.setValue(30.00f);
        msg2.setOperation("add");
        msgInstance.addMessage(msg2);

        List<Message> filteredMsg = msgInstance.getTypeIIIMessages(0);

        assertEquals(filteredMsg.size(), 1);
    }

    @Test
    void getMessages() {
        MessageRepository msgInstance = MessageRepository.getInstance();
        Message msg = new Message();
        msgInstance.messages = new ArrayList<>();

        msg.setProductType("apple");
        msg.setSales(30);
        msg.setValue(10f);
        msgInstance.addMessage(msg);
        Message msg1 = new Message();
        msg1.setProductType("orange");
        msg1.setSales(100);
        msg1.setValue(10.00f);
        msgInstance.addMessage(msg1);
        Message msg2 = new Message();
        msg2.setProductType("Orange");
        msg2.setSales(10);
        msg2.setValue(30.00f);
        msg2.setOperation("add");
        msgInstance.addMessage(msg2);

        List<Message> getMessages = msgInstance.getMessages();
        assertEquals(getMessages.size(), 2);
    }
}