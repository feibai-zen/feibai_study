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

import java.util.Set;

/**
 * Spring List测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestList {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;


  public void test_push() {

  }

}
