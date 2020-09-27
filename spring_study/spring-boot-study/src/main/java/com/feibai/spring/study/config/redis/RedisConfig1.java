package com.feibai.spring.study.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig1 extends RedisPoolConfig {

  @Value("${redis.db1}")
  private int dbIndex;

  @Value("${lamia.redis.host1}")
  private String host;

  @Value("${redis.port1}")
  private int port;

  @Value("${redis.password1}")
  private String password;

  @Value("${redis.timeout1}")
  private int timeout;

  /**
   * 配置redis连接工厂
   */
  @Bean(name = "redisFactory1")
  public RedisConnectionFactory redisConnectionFactory() {
    return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
  }


  /**
   * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
   */
  @Bean(name = "redisTemplate1")
  public RedisTemplate acquireGiftRedisTemplate() {
    RedisTemplate template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory());
    setSerializer(template);
    return template;
  }

}
