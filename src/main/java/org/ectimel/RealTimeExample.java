package org.ectimel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RealTimeExample {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject json = new JSONObject();
        json.put("from_date", "01-Jan-2023");
        json.put("to_date", "29-Dec-2023");
        json.put("email", "dtworek94@gmail.com");
        json.put("query", "select * from database");
        json.put("a", "alphabetical order");

        channel.basicPublish("", "Queue-1", null, json.toString().getBytes());

        channel.close();
        connection.close();

    }
}
