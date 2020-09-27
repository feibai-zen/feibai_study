package com.feibai.spring.study.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * spring-boot-data-packing 设置Redis多实例的基类
 */
@Configuration
public class RedisPoolConfig {

  @Value("${redis.maxTotal}")
  private int maxTotal;

  @Value("${redis.maxIdle}")
  private int maxIdle;
  @Value("${redis.minIdle}")
  private int minIdle;

  @Value("${redis.maxWaitMillis}")
  private int maxWaitMillis;

  @Value("${redis.minEvictableIdleTimeMillis}")
  private int minEvictableIdleTimeMillis;

  @Value("${redis.softMinEvictableIdleTimeMillis}")
  private int softMinEvictableIdleTimeMillis;

  @Value("${redis.timeBetweenEvictionRunsMillis}")
  private int timeBetweenEvictionRunsMillis;

  @Value("${redis.numTestsPerEvictionRun}")
  private int numTestsPerEvictionRun;


  /**
   * 创建redis连接工厂
   */
  public JedisConnectionFactory createJedisConnectionFactory(int dbIndex, String host, int port,
                                                             String password, int timeout) {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setDatabase(dbIndex);
    jedisConnectionFactory.setHostName(host);
    jedisConnectionFactory.setPort(port);
    jedisConnectionFactory.setPassword(password);
    jedisConnectionFactory.setTimeout(timeout);
    //设置pool
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(maxTotal);
    poolConfig.setMaxIdle(maxIdle);
    poolConfig.setMinIdle(minIdle);
    poolConfig.setMaxWaitMillis(maxWaitMillis);
    poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    poolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
    poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
    poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    jedisConnectionFactory.setPoolConfig(poolConfig);
    return jedisConnectionFactory;
  }

  /**
   * 设置RedisTemplate的序列化方式
   */
  public void setSerializer(RedisTemplate redisTemplate) {
    redisTemplate.setDefaultSerializer(new StringRedisSerializer());
  }
}