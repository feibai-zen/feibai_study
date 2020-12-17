package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.SpringbootStudyStartup;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Spring List测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootStudyStartup.class)
public class RedisTestList {

  @Autowired
  @Qualifier("redisTemplate2")
  private RedisTemplate<String, String> redisTemplate;


  public void test_push() {

  }

}
