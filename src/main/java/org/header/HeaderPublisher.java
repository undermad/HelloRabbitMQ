package org.header;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeaderPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message is here!";

        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        AMQP.BasicProperties br = new AMQP.BasicProperties()
                .builder()
                .headers(headersMap)
                .build();


        channel.basicPublish("Header-Exchange", "", br, message.getBytes());

        channel.close();
        connection.close();


    }
}
