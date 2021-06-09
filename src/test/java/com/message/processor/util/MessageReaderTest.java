package com.message.processor.util;

import com.message.processor.model.Message;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageReaderTest {

    @Test
    void readTextMessages() throws IOException {
        MessageReader reader = new MessageReader();
        List<Message> messages = reader.readTextMessages();
        Message msg = new Message();
        msg.setValue(10f);
        msg.setSales(10);
        msg.setProductType("apple");
        messages.forEach(message -> System.out.println(message.getProductType()));
        assertNotNull(messages);
        assertEquals(messages.size(), 10);
        assertEquals(messages.get(4).getValue(), msg.getValue());
    }

    @Test
    void readJsonMessages() throws IOException {
        MessageReader reader = new MessageReader();
        List<Message> messages = reader.readJsonMessages();
        Message msg = new Message();
        msg.setValue(10f);
        msg.setSales(10);
        msg.setProductType("apple");
        assertNotNull(messages);
        assertEquals(messages.size(), 10);
        assertEquals(messages.get(4).getValue(), msg.getValue());
    }
}