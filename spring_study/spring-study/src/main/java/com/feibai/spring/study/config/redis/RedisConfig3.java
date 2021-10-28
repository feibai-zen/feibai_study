package com.feibai.spring.study.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig3 extends RedisPoolConfig {

  @Value("${redis.db3}")
  private int dbIndex;

  @Value("${redis.host3}")
  private String host;

  @Value("${redis.port3}")
  private int port;

  @Value("${redis.password3}")
  private String password;

  @Value("${redis.timeout3}")
  private int timeout;

  /**
   * 配置redis连接工厂
   */
  @Bean(name = "redisFactory3")
  public RedisConnectionFactory redisConnectionFactory3() {
    return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
  }


  /**
   * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
   */
  @Bean(name = "redisTemplate3")
  public RedisTemplate acquireRedisTemplate3() {
    RedisTemplate template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory3());
    setSerializer(template);
    return template;
  }

}