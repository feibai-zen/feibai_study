package com.feibai.spring.study.test;

import com.feibai.spring.study.App;
import com.feibai.spring.study.common.IRedisKeySpace;
import com.feibai.spring.study.common.RedisKeySpaceEnum;
import com.google.common.base.Charsets;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Spring pipeline测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestPipeline {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  private IRedisKeySpace redisKeyCreator = RedisKeySpaceEnum.V2;

  @Test
  public void testPipeline() {
    String zkey = "sping.boot.redis.pipeline";
    List<String> memberList = Lists.newArrayList();
    memberList.add("liyuanlong");
    memberList.add("yangzongqin");

    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      for (String name : memberList) {
        connection.zIncrBy(zkey.getBytes(Charsets.UTF_8), 10, serializer.serialize(name));
      }
      return null;
    });
  }

  @Test
  public void testGetPipeline() {
    String zKey = "sping.boot.redis.pipeline";
    Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(zKey, 0, 99);
    if (CollectionUtils.isEmpty(tuples)) {
      System.out.println("===============================");
      System.out.println(zKey + "is empty");
    } else {
      while (tuples.iterator().hasNext()) {
        Double score = tuples.iterator().next().getScore();
        System.out.println("===============================");
        System.out.println(score);
      }
    }
  }

}