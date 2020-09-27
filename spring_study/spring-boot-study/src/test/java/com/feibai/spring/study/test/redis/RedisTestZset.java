package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.SpringbootStudyStartup;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Spring Zset测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootStudyStartup.class)
@Slf4j
public class RedisTestZset {

  @Autowired
  private StringRedisTemplate redisTemplate;

  private String test_zkey = "springboot.study.redis.zset";

  @Test
  public void test_reverseRangeWithScores() {
    Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(test_zkey, 0, 99);
    List<Double> scores = Lists.newArrayList();
    if (CollectionUtils.isEmpty(tuples)) {
      log.warn("redis set is empty.");
      return;
    } else {
      while (tuples.iterator().hasNext()) {
        Double score = tuples.iterator().next().getScore();
        scores.add(score);
      }
    }
  }

  @Test
  public void test_incrementScore() {
    Double increScore = new Double("100");
    String member = "test_incrementScore";
    Double resScore = redisTemplate.opsForZSet().incrementScore(test_zkey, member, increScore);
  }

  /**
   * 倒序的排名。score从高向低排序。
   */
  @Test
  public void test_reverseRank() {
    String name = "test_reverseRank";
    Long rank = redisTemplate.opsForZSet().reverseRank(test_zkey, String.valueOf(name));
  }

  /**
   * 添加member
   */
  @Test
  public void test_add() {
    String member = "test_add";
    Boolean success = redisTemplate.opsForZSet().add(test_zkey, member, 1);
  }

  /**
   * 倒序members
   */
  public void test_reverseRange() {
    Set<String> set = redisTemplate.opsForZSet().reverseRange(test_zkey, 0, 99);
  }

  /**
   * 正序
   */
  public void test_rank() {
    String member = "test_rank";
    redisTemplate.opsForZSet().add(test_zkey, member, 10000);
    Long rank = redisTemplate.opsForZSet().rank(test_zkey, member);
  }

}
