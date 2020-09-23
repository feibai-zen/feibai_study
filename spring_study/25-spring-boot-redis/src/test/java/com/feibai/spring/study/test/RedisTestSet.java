package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestSet {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Test
  public void test_ismember() {
    String key = "spring.boot.redis.test.set";
    String name = "liyuanlong";
    Boolean res = redisTemplate.opsForSet().isMember(key, name);
  }

  /**
   * 删除member
   */
  @Test
  public void test_remove() {
    redisTemplate.opsForSet().remove("sKey", String.valueOf("member"));
  }

}
