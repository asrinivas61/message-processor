package com.message.processor.util;

import com.message.processor.model.Message;
import org.apache.commons.lang3.StringUtils;

public class MessageClassifier {

    private final String  TYPEI = "type1";
    private final String  TYPEII = "type2";
    private final String  TYPEIII = "type3";

    public static final String ADD = "add";
    public static final String SUBTRACT = "subtract";
    public static final String MULTIPLY = "multiply";

    public Message parse(String input) {
        Message message = null;
        input = input.trim();
        String[] words = input.split(" ");
        if(words.length  < 3){
            // dont throw exception
            return null;
        }

        // To check for the message type
        if (words[0].equalsIgnoreCase(ADD) || words[0].equalsIgnoreCase(SUBTRACT)
                || words[0].equalsIgnoreCase(MULTIPLY)) {
            message = createMessage(words, TYPEIII);
        } else if (StringUtils.isNumeric(words[0])) {
            message =  createMessage(words, TYPEII);
        } else {
            message = createMessage(words, TYPEI);
        }
        return message;
    }

    private Message createMessage(String[] words, String messageType) {
        Message message = new Message();

        switch (messageType) {
            case TYPEI:
                message.setProductType(words[0]);
                message.setSales(1);
                message.setOperation("");
                message.setValue(getPriceValue(words[2]));
                break;

            case TYPEII:
                message.setSales(Integer.parseInt(words[0]));
                message.setOperation("");
                message.setProductType(words[1]);
                message.setValue(getPriceValue(words[3]));
                break;

            case TYPEIII:
                message.setOperation(words[0]);
                message.setProductType(words[2]);
                message.setSales(0);
                message.setValue(getPriceValue(words[1]));
                break;
        }
        return message;
    }

    /**
     * method getPriceValue
     * @param word
     * @return float product value
     */
    private float getPriceValue(String word) {
        float price = 0;
        if (Character.isDigit(word.charAt(0)) && (Character.isAlphabetic(word.charAt(word.length() -1)))) {
            price = new Float(word.substring(0, word.length()-1));
        }
        return price;
    }
}