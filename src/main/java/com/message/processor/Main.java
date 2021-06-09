package com.message.processor;

import com.message.processor.model.Message;
import com.message.processor.repository.ProductRepository;
import com.message.processor.service.MessageProcessor;
import com.message.processor.util.Config;
import com.message.processor.util.MessageClassifier;

import java.util.Scanner;

public class Main {

    static int counter = 0;
    static int ADJUSTMENT_COUNT = Integer.parseInt(Config.getConfig("ADJUSTMENT_COUNTER"));
    static int LOG_COUNT = Integer.parseInt(Config.getConfig("LOG_COUNTER"));

    public static void main(String... args) {
        MessageProcessor processor = new MessageProcessor();
        ProductRepository productRepository = ProductRepository.getInstance();
        MessageClassifier classifier = new MessageClassifier();
        int from =0;

        System.out.println(("Enter Message: "));
        while (true) {
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            input += scan.nextLine();
            Message message = classifier.parse(input);
            counter +=1;
            processor.processMessage(message);
            if (counter % LOG_COUNT == 0) {
                System.out.println("-----------------------------------------");
                System.out.println("Product Name | Total Sales | Total Price ");
                productRepository.view();
                System.out.println("-----------------------------------------");
            }
            if (counter % ADJUSTMENT_COUNT == 0) {
                processor.calculateAdjustments(from);
                from +=ADJUSTMENT_COUNT;
            }
        }
    }
}