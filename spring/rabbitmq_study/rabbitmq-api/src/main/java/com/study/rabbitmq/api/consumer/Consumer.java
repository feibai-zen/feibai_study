package com.study.rabbitmq.api.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {

  public static void main(String[] args) throws Exception {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("111.230.115.242");
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("feibai");
    connectionFactory.setPassword("123456");
    connectionFactory.setVirtualHost("/");
    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();

    String exchangeName = "test_consumer_exchange";
    String routingKey = "consumer.#";
    String queueName = "test_consumer_queue";

    channel.exchangeDeclare(exchangeName, "topic", true, false, null);
    channel.queueDeclare(queueName, true, false, false, null);
    channel.queueBind(queueName, exchangeName, routingKey);

    channel.basicConsume(queueName, true, new MyConsumer(channel));
  }
}
