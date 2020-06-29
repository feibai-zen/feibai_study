package com.study.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Procuder {

  public static void main(String[] args) throws Exception {
    //1 创建一个ConnectionFactory, 并进行配置
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("111.230.115.242");
    connectionFactory.setPort(5672);
    connectionFactory.setVirtualHost("/");

    //2 通过连接工厂创建连接
    Connection connection = connectionFactory.newConnection();

    //3 通过connection创建一个Channel
    Channel channel = connection.createChannel();

    //4 通过Channel发送数据
    for (int i = 0; i < 5; i++) {
      String msg = "Hello RabbitMQ!";
      channel.basicPublish("", "test001", null, msg.getBytes());
      //public void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body);
      //1:exchange   2:routingKey
      //3.props:消息的属性   4.body:消息
      //关键点:如果exchange没有指定，则默认走AMQP default.如果没有指定Queue，则按照routingKey找相同名称的队列，如果找到，就投递到
      //该队列，找不到则发送失败。
    }
    //5 记得要关闭相关的连接
    channel.close();
    connection.close();
  }
}
