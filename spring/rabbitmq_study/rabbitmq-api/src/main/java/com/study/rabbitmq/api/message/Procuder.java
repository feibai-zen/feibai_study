package com.study.rabbitmq.api.message;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP;
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

    Map<String, Object> headers = new HashMap<>();
    headers.put("my1", "111");
    headers.put("my2", "222");

    AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
        .deliveryMode(2)//1:非持久化投递  2：持久化的投递，服务重启后,消息依然存在
        .contentEncoding("UTF-8")//字符集
        .expiration("10000")//过期时间(毫秒),超出过期时间还没有被消费,就会消失
        .headers(headers)//增加自定义属性
        .build();

    //4 通过Channel发送数据
    for (int i = 0; i < 5; i++) {
      String msg = "Hello RabbitMQ!";
      //1 exchange   2 routingKey
      channel.basicPublish("", "test001", properties, msg.getBytes());
    }

    //5 记得要关闭相关的连接
    channel.close();
    connection.close();
  }
}
