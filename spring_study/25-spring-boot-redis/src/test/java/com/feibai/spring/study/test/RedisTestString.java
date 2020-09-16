package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    String value = (String) this.redisTemplate.opsForValue().get(key);
    System.out.println(value);
  }

  @Test
  public void testSetWithJsonSerializer() {
    this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    this.redisTemplate.opsForValue().set("spring.boot.redis.test.string.json", "我是个中文字符串，请在redis查看是否乱码啊....");
  }

  @Test
  public void testGetWithJsonSerializer() {
    String key = "spring.boot.redis.test.string.json";
    // 逆序列化处理
    this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    String value = (String) this.redisTemplate.opsForValue().get(key);
    System.out.println(value);
  }

  @Test
  public void test_setIfAbsent() {
    Boolean lock = redisTemplate.opsForValue().setIfAbsent("spring.boot.redis.test.string.if.absent", "lock", 200, TimeUnit.MILLISECONDS);
    if (!Objects.isNull(lock) && lock) {
      System.out.println("设置key成功");
    } else {
      System.out.println("已经存在这个key");
    }
  }

  @Test
  public void test_getSet() {
    String key = "spring.boot.redis.test.string";
    //返回给定 key 的旧值。 当 key没有旧值时，即key不存在时，返回nil。当key存在但不是字符串类型时，返回一个错误。
    redisTemplate.opsForValue().getAndSet(key, "haha");
  }

  /**
   * 查询key的过期时间，如果key不存在，就设置键。如果存在，增加过期时间
   */
  @Test
  public void test_incrExpireTime() {
    long expireMills = 60000L;

    String key = "spring.boot.redis.test.string";
    boolean isSet = redisTemplate.opsForValue().setIfAbsent(key, "1", expireMills, TimeUnit.MILLISECONDS);
    if (!isSet) {
      long oldExpires = redisTemplate.getExpire(key);
      redisTemplate.opsForValue().set(key, "1", expireMills + oldExpires, TimeUnit.MILLISECONDS);
    }
  }

}
