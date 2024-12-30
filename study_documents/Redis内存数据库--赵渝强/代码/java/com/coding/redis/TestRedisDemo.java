package com.coding.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TestRedisDemo {

	//使用普通的操作往Redis中插入10000条数据
	@Test
	public void testNormalCommand() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		
		//记录操作起始时间
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			client.set("key"+i, "value"+i);
		}
		
		//记录操作结束的时间
		long end = System.currentTimeMillis();
		
		System.out.println("普通方式执行的时间：" + (end-start));
		
		client.close();
	}
	
	@Test
	public void testPipeLineCommand() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		//创建管道
		Pipeline pl = client.pipelined();
		
		//记录操作起始时间
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			pl.set("pipelinekey"+i, "pipelinevalue"+i);
		}
		
		pl.sync();
		
		//记录操作结束的时间
		long end = System.currentTimeMillis();
		
		System.out.println("管道方式执行的时间：" + (end-start));
		
		client.close();		
	}
}































