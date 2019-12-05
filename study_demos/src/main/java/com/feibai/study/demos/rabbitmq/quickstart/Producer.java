package com.feibai.study.demos.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {
  private static final Logger logger = LoggerFactory.getLogger(Producer.class);

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

      // 4 通过channel发送数据
      for (int i = 0; i < 5; i++) {
        String msg = "Hello RabbitMQ.";
        //public void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body); //props:消息的属性  body:消息
        channel.basicPublish("", "test001", null, msg.getBytes());
      }

      // 5 记得要关闭相关的连接
      channel.close();
      connection.close();
    } catch (Exception ex) {
      logger.error("publish msg failed.");
    }

  }

}