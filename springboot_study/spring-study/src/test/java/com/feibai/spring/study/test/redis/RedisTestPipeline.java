package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.FeibaiStudyStartup;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring pipeline测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeibaiStudyStartup.class)
public class RedisTestPipeline {

  @Autowired
  @Qualifier("redisTemplate1")
  private RedisTemplate<String, String> redisTemplate;

  private String pipelineZkey = "sping.boot.redis.pipeline.zkey";

  /**
   * zkey、member需要序列化
   */
  @Test
  public void testPipeline1() {
    String zkey = "sping.boot.redis.pipeline1";
    List<String> memberList = Lists.newArrayList();
    memberList.add("liyuanlong");
    memberList.add("yangzongqin");

    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      for (String member : memberList) {
        connection.zIncrBy(serializer.serialize(zkey), 10, serializer.serialize(member));
      }
      return null;
    });
  }

  public void testPipeline2() {
    String zkey = "sping.boot.redis.pipeline2";
    List<String> memberList = Lists.newArrayList();
    memberList.add("liyuanlong");
    memberList.add("yangzongqin");

    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
    redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      for (String member : memberList) {
        connection.set(serializer.serialize(zkey + "_anchor"), serializer.serialize(member));
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
      System.out.println("查询到的数据有：" + tuples.size() + "条");

      Iterator<ZSetOperations.TypedTuple<String>> iterator = tuples.iterator();
      while (iterator.hasNext()) {
        Double score = iterator.next().getScore();
        System.out.println("===============================");
        System.out.println(score);
      }
    }
  }

  /**
   * 获取pipeline执行的
   */
  @Test
  public void testGetResultPipeline() {
    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
    List<Object> resultList = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
      connection.zIncrBy(serializer.serialize(pipelineZkey), 100, serializer.serialize("liyuanlong"));
      connection.zRevRange(serializer.serialize(pipelineZkey), 0, 1);
      return null;
    });

    //pipeline执行结果的顺序按照提交顺序。执行的顺序不一定按照提交顺序。上面的zRevRange执行顺序可能在zIncrBy指令之后执行。
    List<String> values = ((LinkedHashSet<String>) resultList.get(1)).stream().collect(Collectors.toList());
    System.out.println(values);
  }

}