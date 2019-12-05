package com.my.study.rabbitmq.quickstart;

import com.rabbitmq.client.*;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
  private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

  public static void main(String[] args) {
    try {
      // 1 创建并设置ConnectionFactory
      ConnectionFactory connectionFactory = new ConnectionFactory();
      connectionFactory.setHost("111.230.115.242");
      connectionFactory.setPort(5672);
      connectionFactory.setVirtualHost("/");

      // 2 通过连接工厂创建连接
      Connection connection = connectionFactory.newConnection();

      // 3 通过connection创建一个channel
      Channel channel = connection.createChannel();

      // 4.声明（创建）一个队列
      String queueName = "test001";
      channel.queueDeclare(queueName, true, false, false, null);

      // 5.创建消费者
      QueueingConsumer queueingConsumer = new QueueingConsumer (channel);

      // 6.设置channel
      channel.basicConsume(queueName,true, queueingConsumer);

      while(true) {
        // 7.获取消息
        Delivery delivery = queueingConsumer.nextDelivery();//获取下一条消息,另外一个重载函数，含有超时时间。
        Envelope envelope = delivery.getEnvelope();
        envelope.getDeliveryTag();
        envelope.getExchange();
        envelope.getRoutingKey();
        envelope.getDeliveryTag();
        System.out.println("消费端"+new String(delivery.getBody()));
        Thread.sleep(1000);
      }

    } catch (Exception ex) {

    }
  }

}
