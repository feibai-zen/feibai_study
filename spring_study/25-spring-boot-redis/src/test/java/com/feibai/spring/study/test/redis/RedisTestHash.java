package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.App;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

/**
 * Spring Hash测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestHash {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;


  @Test
  public void test_putHash() {
    String hashKey = "sping.boot.redis.hashmap";
    Map<String, String> userNames = Maps.newConcurrentMap();
    userNames.put("lichuanjin", String.valueOf(54));
    userNames.put("luoli", String.valueOf(52));
    userNames.put("liyuanlong", String.valueOf(30));
    userNames.put("lirui", String.valueOf(28));
    userNames.put("yangzongqin", String.valueOf(28));
    redisTemplate.opsForHash().putAll(hashKey, userNames);
  }

  @Test
  public void test_getHash() {
    String hashKey = "sping.boot.redis.hashmap";
    List<Object> userNames = Lists.newArrayListWithCapacity(5);
    userNames.add("lichuanjin");
    userNames.add("luoli");
    userNames.add("liyuanlong");
    userNames.add("lirui");
    userNames.add("yangzongqin");
    List<Object> values = redisTemplate.opsForHash().multiGet(hashKey, userNames);

    //返回的value按照传入list的顺序
    values.forEach(value -> System.out.println(value.toString()));
  }


  private void test_lua(){


  }
}
