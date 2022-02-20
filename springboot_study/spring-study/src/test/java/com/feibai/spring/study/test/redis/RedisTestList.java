package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.FeibaiStudyStartup;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring List测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FeibaiStudyStartup.class)
public class RedisTestList {

  @Autowired
  @Qualifier("redisTemplate2")
  private RedisTemplate<String, String> redisTemplate;


  public void test_push() {

  }

}
