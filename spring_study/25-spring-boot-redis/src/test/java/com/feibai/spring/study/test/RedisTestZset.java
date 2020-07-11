package com.feibai.spring.study.test;


import com.feibai.spring.study.App;
import com.feibai.spring.study.common.IRedisKeySpace;
import com.feibai.spring.study.common.RedisKeySpaceEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestZset {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  private IRedisKeySpace redisKeyCreator = RedisKeySpaceEnum.V2;

  /**
   * 添加一个字符串
   */
  @Test
  public void testSet() {
//    Iterator<Integer> iterator = IntStream.range(0, 20).boxed().iterator();
    System.out.println("========================");
    System.out.println(Instant.now());
    this.redisTemplate.opsForValue().set("key", "北京尚学堂");



  }

}
