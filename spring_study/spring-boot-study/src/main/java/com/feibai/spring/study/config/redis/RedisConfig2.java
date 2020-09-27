package com.feibai.spring.study.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author feibai
 */
@Configuration
public class RedisConfig2 extends RedisPoolConfig {

  @Value("${redis.db2}")
  private int dbIndex;

  @Value("${redis.host2}")
  private String host;

  @Value("${redis.port2}")
  private int port;

  @Value("${redis.password2}")
  private String password;

  @Value("${redis.timeout2}")
  private int timeout;

  /**
   * 配置redis连接工厂
   */
  @Bean(name = "lotteryRedisFactory")
  public RedisConnectionFactory lotteryRedisConnectionFactory() {
    return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
  }


  /**
   * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
   */
  @Bean(name = "lotteryRedisTemplate")
  public RedisTemplate acquireLotteryRedisTemplate() {
    RedisTemplate template = new RedisTemplate();
    template.setConnectionFactory(lotteryRedisConnectionFactory());
    setSerializer(template);
    return template;
  }

}
