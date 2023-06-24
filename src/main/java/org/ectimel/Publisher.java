package org.ectimel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String[] messages = {"First Message", "Second Message", "Third Message", "Fourth Message!"};

        for (String message : messages){
            channel.basicPublish("", "Queue-1", null, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}