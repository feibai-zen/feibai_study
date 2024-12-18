package com.study.rabbitmq.api.consumer;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer extends DefaultConsumer {

  public MyConsumer(Channel channel) {
    super(channel);
  }

  @Override
  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
    System.err.println("-----------consume message----------");
    System.err.println("consumerTag: " + consumerTag);//broker自动生成的tag
    System.err.println("envelope: " + envelope);//
    System.err.println("properties: " + properties);
    System.err.println("body: " + new String(body));
  }
}
