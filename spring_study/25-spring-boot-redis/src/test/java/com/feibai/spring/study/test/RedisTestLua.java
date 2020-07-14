package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import com.feibai.spring.study.common.JedisPoolUtils;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.collections.Maps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Spring Hash测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestLua {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  /**
   * 实现一个对IP的限流
   */

  /**
   * local num = redis.call('incr', KEYS[1])
   * if tonumber(num) == 1 then
   * redis.call('expire', KEYS[1], ARGV[1])
   * return 1
   * elseif tonumber(num) > tonumber(ARGV[2]) then
   * return 0
   * else
   * return 1
   * end
   */

  public void test_lua() {
    JedisPool jedisPool = JedisPoolUtils.getInstance();
    Jedis jedis = jedisPool.getResource();
    try {
      String lua = "local num = redis.call('incr', KEYS[1])\n" +
              "if tonumber(num) == 1 then\n" +
              "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
              "\treturn 1\n" +
              "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
              "\treturn 0\n" +
              "else \n" +
              "\treturn 1\n" +
              "end\n";
      Object result = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("localhost"), Arrays.asList("10", "2"));
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (jedis != null) {
        try {
          jedis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }


}