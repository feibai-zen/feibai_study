package com.feibai.spring.study.test;


import com.feibai.spring.study.App;
import com.feibai.spring.study.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring String测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestString {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 添加一个字符串
   */
  @Test
  public void testSet() {
    this.redisTemplate.opsForValue().set("key", "北京尚学堂");
  }

  /**
   * 获取一个字符串
   */
  @Test
  public void testGet() {
    String value = (String) this.redisTemplate.opsForValue().get("key");
    System.out.println(value);
  }

}
