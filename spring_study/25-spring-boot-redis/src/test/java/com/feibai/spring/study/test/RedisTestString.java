package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
   * <p>
   * 添加入库的字符串是编码后的字符串，默认使用的是JdkSerializationRedisSerializer
   */
  @Test
  public void testSet() {
    this.redisTemplate.opsForValue().set("spring.boot.redis.test.string", "我是个中文字符串，请在redis查看是否乱码啊....");
  }

  /**
   * 获取一个字符串
   */
  @Test
  public void testGet() {
    String key = "spring.boot.redis.test.string";
    //取出字符串的过程中，经过了JdkSerializationRedisSerializer
    // 逆序列化处理
    String value = (String) this.redisTemplate.opsForValue().get(key);
    System.out.println(value);
  }

}
