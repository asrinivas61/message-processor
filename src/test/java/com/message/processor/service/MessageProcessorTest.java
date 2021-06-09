package com.message.processor.service;

import com.message.processor.model.Message;
import com.message.processor.repository.MessageRepository;
import com.message.processor.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageProcessorTest {

    @Test
    void processMessageWithOperation() {
        MessageProcessor msgProcess = new MessageProcessor();
        MessageRepository msgRepo = MessageRepository.getInstance();
        Message sampleMsg = new Message();

        sampleMsg.setProductType("apple");
        sampleMsg.setSales(10);
        sampleMsg.setValue(20f);
        sampleMsg.setOperation("add");

        msgProcess.processMessage(sampleMsg);

        assertEquals(msgRepo.getMessages().size(), 0 );
    }

    @Test
    void processMessageWithOutOperation() {
        MessageProcessor msgProcess = new MessageProcessor();
        ProductRepository prodRepo = ProductRepository.getInstance();
        Message sampleMsg = new Message();

        sampleMsg.setProductType("apple");
        sampleMsg.setSales(10);
        sampleMsg.setValue(20f);
        msgProcess.processMessage(sampleMsg);

        assertEquals(prodRepo.get(sampleMsg.getProductType()).getPrice(), sampleMsg.getValue() * sampleMsg.getSales());
        assertEquals((int) prodRepo.get(sampleMsg.getProductType()).getQuantity(), (int) sampleMsg.getSales());
    }

    @Test
    void calculateAdjustments() {
        MessageRepository msgInstance = MessageRepository.getInstance();
        ProductRepository prodRepo = ProductRepository.getInstance();
        MessageProcessor msgProc = new MessageProcessor();
        Message msg = new Message();

        msg.setProductType("orange");
        msg.setSales(30);
        msg.setValue(10f);
        msgInstance.addMessage(msg);
        Message msg1 = new Message();
        msg1.setProductType("orange");
        msg1.setSales(100);
        msg1.setValue(10.00f);
        msgInstance.addMessage(msg1);
        Message msg2 = new Message();
        msg2.setProductType("orange");
        msg2.setSales(10);
        msg2.setValue(30.00f);
        msg2.setOperation("add");
        msgInstance.addMessage(msg2);

        msgProc.calculateAdjustments(2);

        assertEquals(prodRepo.get(msg2.getProductType()).getPrice(), msg.getSales() * msg.getValue() + msg1.getSales() * msg.getValue());
    }
}