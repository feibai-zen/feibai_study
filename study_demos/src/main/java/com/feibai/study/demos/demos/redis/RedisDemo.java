package com.feibai.study.demos.demos.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * redis中有哪些命令，Jedis中就有哪些方法
 * 
 * @author leeyuanlong
 */
public class RedisDemo {

	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getJedis();
		String key = "users";

		if (jedis.exists(key)) {
			Map<String, String> users = jedis.hgetAll(key);
			System.out.println("redis中查询的结果是： " + users);

		} else {
			jedis.hset(key, "id", "1");
			jedis.hset(key, "name", "liyuanlong");
			jedis.hset(key, "age", "29");
			jedis.hset(key, "comment", "handsome");
			System.out.println("redis中没有数据");
		}

		jedis.close();

	}

}
