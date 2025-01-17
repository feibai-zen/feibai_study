package com.study.rabbitmq.api.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

  public static void main(String[] args) throws Exception {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("111.230.115.242");
    connectionFactory.setUsername("feibai");
    connectionFactory.setPassword("123456");
    connectionFactory.setPort(5672);
    connectionFactory.setVirtualHost("/");

    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();

    String exchange = "test_consumer_exchange";
    String routingKey = "consumer.save";

    String msg = "Hello RabbitMQ Consumer Message";

    for (int i = 0; i < 5; i++) {
      channel.basicPublish(exchange, routingKey, true, null, msg.getBytes());
    }

  }
}
