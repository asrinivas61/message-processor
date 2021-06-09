package com.message.processor.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.processor.model.Message;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MessageReader {

    /***
     * To read json messages from a file
     * method readJsonMessages
     * @return messages
     * @throws IOException
     */
    public  List<Message> readJsonMessages() throws IOException {

        File file = new File(
                this.getClass().getClassLoader().getResource("input.json").getFile());
        ObjectMapper mapper = new ObjectMapper();
        List<Message> messages = mapper.readValue(file, new TypeReference<List<Message>>(){});
        return messages;
    }

    /***
     * To read text messages from a file
     * method readTextMessages
     * @return messages
     * @throws IOException
     */
    public  List<Message> readTextMessages() throws IOException {

        List<Message> messages = new ArrayList<>();
        Scanner myReader = new Scanner(readFile("input.txt"));

        MessageClassifier classifier = new MessageClassifier();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (!StringUtils.isEmpty(data)) {
                Optional<Message> message = Optional.of(classifier.parse(data));
                if (message.isPresent())
                    messages.add(message.get());
            }
        }
        myReader.close();
        return messages;
    }

    private File readFile(String fileName) {
        File file = null;
        try {
            file = new File(
                    this.getClass().getClassLoader().getResource(fileName).getFile());
        } catch (Exception e) {
            System.out.println("Exception while reading file.");
            e.printStackTrace();
        }
        return file;
    }
}