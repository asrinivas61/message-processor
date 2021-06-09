package com.message.processor.repository;

import com.message.processor.model.Message;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageRepository {

    static List<Message> messages = null;
    private MessageRepository() {}

    private static MessageRepository messageRepository = null;

    public static MessageRepository getInstance() {
        if (messageRepository == null){
            messageRepository = new MessageRepository();
            messages = new ArrayList<>();
        }
        return messageRepository;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public int get_size(){
        return messages.size();
    }

    /***
     * To fetch only Type III messages
     * @return
     */
    public List<Message> getTypeIIIMessages(int from) {
        return messages.stream().skip(from).filter(message -> !StringUtils.isEmpty(message.getOperation())).collect(Collectors.toList());
    }

    /***
     * To Fetch Type 1 and Type II Messages
     * @return
     */
    public List<Message> getMessages() {
        return messages.stream().filter(message -> StringUtils.isEmpty(message.getOperation())).collect(Collectors.toList());
    }
}