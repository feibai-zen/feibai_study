package com.feibai.spring.study.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.ArrayList;

@Configuration
public class RedisConfig1 extends RedisPoolConfig {

  @Value("${redis.db1}")
  private int dbIndex;

  @Value("${redis.host1}")
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
  @Primary
  public RedisConnectionFactory redisConnectionFactory1() {
    return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
  }

  /**
   * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
   */
  @Bean(name = "redisTemplate1")
  public RedisTemplate acquireRedisTemplate1() {
    RedisTemplate template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory1());
    setSerializer(template);
    return template;
  }


  @Bean(name = "redisScriptSwitchSeasonRound")
  public DefaultRedisScript getRedisScriptClient1() {
    DefaultRedisScript<ArrayList> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("classpath:lua/switchSeasonRound.lua")));
    redisScript.setResultType(ArrayList.class);

    return redisScript;
  }

  @Bean(name = "redisScriptLock")
  public DefaultRedisScript getRedisScriptClient2() {
    DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("classpath:lua/lock.lua")));
    redisScript.setResultType(String.class);

    return redisScript;
  }

  @Bean(name = "redisScriptInCard")
  public DefaultRedisScript getRedisScriptClient3() {
    DefaultRedisScript<Integer> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("classpath:lua/incrCard.lua")));
    redisScript.setResultType(Integer.class);

    return redisScript;
  }

}
