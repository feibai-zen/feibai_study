package com.coding.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {

	@Test
	public void testTransaction() {
		//创建Redis的客户端
		Jedis client = new Jedis("192.168.79.11", 6379);
		
		//创建事务
		Transaction tc = null;
		try {
			tc = client.multi();
			//转账
			tc.decrBy("tom", 100);
			tc.incrBy("mike", 100);
			//提交事务
			tc.exec();
		}catch(Exception ex) {
			ex.printStackTrace();
			//回滚事务
			tc.discard();
		}
		
		client.close();
	}
	
	@Test
	public void testWatch() {
		Jedis client = new Jedis("192.168.79.11", 6379);
		//模拟买票
		
		//给ticket加锁
		client.watch("ticket");
		
		//开启事务
		Transaction tc =null;
		try {
			//开启事务
			tc = client.multi();
			//扣钱
			tc.decrBy("tom", 100);
			//票数减一
			tc.decr("ticket");
			
			Thread.sleep(8000); //睡8秒
			
			//提交事务
			tc.exec();
		}catch(Exception ex) {
			ex.printStackTrace();
			tc.discard();
		}
		client.close();
	}
}














