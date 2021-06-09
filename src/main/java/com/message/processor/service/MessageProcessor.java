package com.message.processor.service;

import com.message.processor.model.Message;
import com.message.processor.model.Product;
import com.message.processor.repository.MessageRepository;
import com.message.processor.repository.ProductRepository;
import com.message.processor.util.MessageClassifier;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageProcessor {

    ProductRepository productRepository = ProductRepository.getInstance();
    MessageRepository messageRepository = MessageRepository.getInstance();

    public void processMessage(Message message) {
        messageRepository.addMessage(message);
        if(StringUtils.isEmpty(message.getOperation())) {
            calculateTotalValue(message);
        }
    }

    /**
     * Calculate total value and update in memory
     * Method: calculateTotalValue
     * @param message
     */
    private void calculateTotalValue(Message message) {
        float total_qty = 0;
        float total_sales = 0;

        if (productRepository.contains(message.getProductType())) {
            Product product = productRepository.get(message.getProductType());
            total_sales = (message.getSales() * message.getValue()) + product.getPrice();
            total_qty = message.getSales() + product.getQuantity();

            Product new_product = new Product(total_qty, total_sales);
            productRepository.append(message.getProductType(), new_product);
        } else {
            productRepository.append(message.getProductType(), new Product(message.getSales(), message.getSales() * message.getValue()));
        }
    }

    /***
     * Calculate Adjustments for all the messages
     * based on the given index
     * Method: calculateAdjustments
     * @param from
     */
    public void calculateAdjustments(int from) {
        System.out.println("Pausing... calculating adjustments");
        System.out.println("Message Repository Size: "+messageRepository.get_size());
        List<Message> messages =  messageRepository.getMessages();

        List<Message> typeIIImessages = messageRepository.getTypeIIIMessages(from);

        if (typeIIImessages!=null && typeIIImessages.size()>=1) {
            List<Message> opMessages = messages.stream().map(message -> {
                for (Message typeIIIMessage : typeIIImessages) {
                    if (message.getProductType().equalsIgnoreCase(typeIIIMessage.getProductType())) {
                        float oldPrice = message.getValue();
                        switch (typeIIIMessage.getOperation().toLowerCase()) {
                            case MessageClassifier.ADD:
                                message.setValue(oldPrice + typeIIIMessage.getValue());
                                break;

                            case MessageClassifier.SUBTRACT:
                                message.setValue(oldPrice - typeIIIMessage.getValue());
                                break;

                            case MessageClassifier.MULTIPLY:
                                message.setValue(oldPrice * typeIIIMessage.getValue());
                                break;

                        }
                    }
                }
                return message;
            }).collect(Collectors.toList());

            updateAdjustments(opMessages);
            productRepository.view();
        }
    }

    /**
     * Update adjusted values in the memory
     * @param messages
     */
    private void updateAdjustments(List<Message> messages) {
        // Delete existing product name
        Set<String> productNames = getUniquProductTypes(messages);
        productNames.forEach(name -> productRepository.remove(name));
        messages.forEach(message -> calculateTotalValue(message));
    }

    private Set getUniquProductTypes(List<Message> messages){
        Set<String> productNames = new HashSet<>();
        for (Message message : messages){
            productNames.add(message.getProductType());
        }
        return productNames;
    }
}