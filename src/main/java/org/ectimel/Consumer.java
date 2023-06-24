package org.ectimel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumeTag, delivery) -> {
            JSONObject json = new JSONObject(new String(delivery.getBody()));
            System.out.println("from date: " + json.get("from_date"));
            System.out.println("to date: " + json.get("to_date"));
            System.out.println("alphabet: " + json.get("a"));
            System.out.println("query: " + json.get("query"));
            System.out.println("email: " + json.get("email"));
        };

        channel.basicConsume("Queue-1", true, deliverCallback, consumerTag -> {});

    }
}