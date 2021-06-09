package com.message.processor.util;

import com.message.processor.model.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageClassifierTest {

    @Test
    void parseTypeIMsg() {
        MessageClassifier msgClas = new MessageClassifier();

        Message parsedStringMsg = msgClas.parse("apple at 10p");

        assertEquals(parsedStringMsg.getProductType(), "apple");
        assertEquals(parsedStringMsg.getValue(), new Float(10));
        assertEquals(parsedStringMsg.getSales(), new Integer(1));
    }

    @Test
    void parseTypeIIMsg() {
        MessageClassifier msgClas = new MessageClassifier();

        Message parsedStringMsg = msgClas.parse("20 apple at 10p");

        assertEquals(parsedStringMsg.getProductType(), "apple");
        assertEquals(parsedStringMsg.getValue(), new Float(10));
        assertEquals(parsedStringMsg.getSales(), new Integer(20));
    }

    @Test
    void parseTypeIIIMsg() {
        MessageClassifier msgClas = new MessageClassifier();

        Message parsedStringMsg = msgClas.parse("Add 20p apples");

        assertEquals(parsedStringMsg.getProductType(), "apple");
        assertEquals(parsedStringMsg.getValue(), new Float(20));
        assertEquals(parsedStringMsg.getSales(), new Integer(0));
        assertEquals(parsedStringMsg.getOperation(), "Add");
    }
}