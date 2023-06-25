package org.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String mobileMessage = "Hello mobile";
        String tvMessage = "Hello tv";
        String acMessage = "Hello ac";

        channel.basicPublish("Direct-Exchange", "mobile", null, mobileMessage.getBytes());
        channel.basicPublish("Direct-Exchange", "tv", null, tvMessage.getBytes());
        channel.basicPublish("Direct-Exchange", "ac", null, acMessage.getBytes());

        channel.close();
        connection.close();
    }
}
