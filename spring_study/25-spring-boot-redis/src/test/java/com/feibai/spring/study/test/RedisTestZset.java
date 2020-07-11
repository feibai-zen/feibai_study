package com.feibai.spring.study.test;


import com.feibai.spring.study.App;
import com.feibai.spring.study.common.IRedisKeySpace;
import com.feibai.spring.study.common.RedisKeySpaceEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.Set;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestZset {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  private IRedisKeySpace redisKeyCreator = RedisKeySpaceEnum.V2;

  /**
   * 添加一个字符串
   */
  @Test
  public void testSet() {
    System.out.println(Instant.now());
    this.redisTemplate.opsForValue().set("key", "我是个中文字符串，请在redis查看是否乱码啊....");
  }

  @Test
  public void testSet1() {
    String redisZKey = "";
    Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(redisZKey, 0, 99);
    if (CollectionUtils.isEmpty(tuples)) {
    } else {
      Double score = tuples.iterator().next().getScore();
    }
  }

  @Test
  public void test01() {
    String zkey = "";
    Double increScore = new Double("100");
    String member = "test_test01";
    Double resScore = redisTemplate.opsForZSet().incrementScore(zkey, member, increScore);
  }
}
