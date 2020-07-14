package com.feibai.spring.study.common;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
  private JedisPoolUtils() {
  }

  private static JedisPool jedisPool;

  public static JedisPool getInstance() {
    if (jedisPool == null) {
      synchronized (JedisPoolUtils.class) {
        if (jedisPool == null) {
          JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
          jedisPoolConfig.setMaxTotal(20);
          jedisPoolConfig.setMaxIdle(10);
          jedisPool = new JedisPool(jedisPoolConfig, "192.168.72.128", 6379);
        }
      }
    }
    return jedisPool;
  }
}