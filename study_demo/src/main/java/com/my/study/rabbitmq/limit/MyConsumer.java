package com.my.study.rabbitmq.limit;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer extends DefaultConsumer {


	private Channel channel ;
	
	public MyConsumer(Channel channel) {
		super(channel);
		this.channel = channel;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
		System.err.println("-----------consume message----------");
		System.err.println("consumerTag: " + consumerTag);
		System.err.println("envelope: " + envelope);
		System.err.println("properties: " + properties);
		System.err.println("body: " + new String(body));

		//3:multiple 是否批量签收  因为在限流的时候，设置为一条一条的发，所以这里设置成false。如果限流时设置多条并发发送，则可以设置为true
		channel.basicAck(envelope.getDeliveryTag(), false);//这条消息已经处理完了，可以发送下一条消息了
		
	}

}
