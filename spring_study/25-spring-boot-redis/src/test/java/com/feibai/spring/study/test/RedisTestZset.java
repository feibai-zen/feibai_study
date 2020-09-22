package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import com.feibai.spring.study.common.IRedisKeySpace;
import com.feibai.spring.study.common.RedisKeySpaceEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestZset {

  @Autowired
  private StringRedisTemplate redisTemplate;
  private IRedisKeySpace redisKeyCreator = RedisKeySpaceEnum.V2;

  @Test
  public void testSet1() {
    String redisZKey = redisKeyCreator.buildStarRankStarHourKey(0);
    Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(redisZKey, 0, 99);
    if (CollectionUtils.isEmpty(tuples)) {
      System.out.println("===============================");
      System.out.println(redisZKey + "is empty");
    } else {
      while (tuples.iterator().hasNext()) {
        Double score = tuples.iterator().next().getScore();
        System.out.println("===============================");
        System.out.println(score);
      }
    }
  }

  @Test
  public void test01() {
    String zkey = redisKeyCreator.buildStarRankStarHourKey(0);
    ;
    Double increScore = new Double("100");
    String member = "test_test01";
    Double resScore = redisTemplate.opsForZSet().incrementScore(zkey, member, increScore);
  }

  /**
   * 倒序的排名。score从高向低排序。
   */
  @Test
  public void test_get_rank() {
    String zkey = "sping.boot.redis.zset";
    String name = "liyuanlong";
    Long rank = redisTemplate.opsForZSet().reverseRank(zkey, String.valueOf(name));
  }

  @Test
  public void test_add() {
    String key = "spring.boot.redis.test.zset";
    redisTemplate.opsForZSet().add(key + "add", " liyuanlong", 1);
  }

  public void test_reverseRange() {
    Set<String> set = redisTemplate.opsForZSet().reverseRange("Zkey", 0, 99);
  }

}
