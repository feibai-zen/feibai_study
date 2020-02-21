package com.study.rabbitmq.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer {

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
		
		//4 声明（创建）一个队列
		String queueName = "test001";

		// queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
		// 1.queue:队列名称
		// 2.durable:是否持久化
		// 3.exclusive:是否独占的方式。设置为true,在一些场景下还是必要的，比如顺序消费，这个队列只能由一个connection监听消费
		// 4.autoDelete:如果这个队列没有与指定exchange建立绑定关系,这个队列会被自动的删除
		// 5.arguments:拓展参数
		channel.queueDeclare(queueName, true, false, false, null);

		//5 创建消费者
		QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
		
		//6 设置Channel
		// basicConsume(String queue, boolean autoAck, Consumer callback)
		// 1.queue:监听的队列  2.autoAck:true-自动签收  3.callback:具体的消费对象
		channel.basicConsume(queueName, true, queueingConsumer);
		
		while(true){
			//7 获取消息（一条）
			Delivery delivery = queueingConsumer.nextDelivery();//阻塞：如果没有消息过来，就一直阻塞在这里
			String msg = new String(delivery.getBody());
			System.err.println("消费端: " + msg);
			Envelope envelope = delivery.getEnvelope();
			envelope.getDeliveryTag();//
			envelope.getExchange();//消息是哪个exchange过来的
			envelope.getRoutingKey();//exchange与队列的routingKey
		}
		
	}
}
