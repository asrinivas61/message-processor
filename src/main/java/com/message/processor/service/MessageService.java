package com.message.processor.service;

import com.message.processor.model.Message;
import com.message.processor.repository.ProductRepository;
import com.message.processor.util.Config;
import com.message.processor.util.MessageReader;

import java.io.InputStream;
import java.util.List;

public class MessageService {

    static int counter = 0;
    static InputStream inputStream;

    static int ADJUSTMENT_COUNT = Integer.parseInt(Config.getConfig("ADJUSTMENT_COUNTER"));
    static int LOG_COUNT = Integer.parseInt(Config.getConfig("LOG_COUNTER"));

    public static void processFile() {
        try {
            MessageProcessor processor = new MessageProcessor();
            ProductRepository productRepository = ProductRepository.getInstance();
            MessageReader reader = new MessageReader();
            List<Message> messages = reader.readTextMessages();
            int from =0;

            for(Message message : messages) {
                counter+=1;
                processor.processMessage(message);
                if (counter % LOG_COUNT == 0){
                    System.out.println("-----------------------------------------");
                    System.out.println("Product Name | Total Sales | Total Price ");
                    productRepository.view();
                    System.out.println("-----------------------------------------");
                }

                if (counter % ADJUSTMENT_COUNT == 0) {
                    processor.calculateAdjustments(from);
                    from += ADJUSTMENT_COUNT;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}