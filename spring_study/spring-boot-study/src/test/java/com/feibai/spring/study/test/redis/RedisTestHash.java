package com.feibai.spring.study.test.redis;

import com.feibai.spring.study.App;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.collections.Maps;

import java.util.List;
import java.util.Map;

/**
 * Spring Hash测试
 */
@RunWith(SpringJUnit4ClassRunner.class)//让junit与spring环境进行整合
@SpringBootTest(classes = App.class)//1. 当前类为springBoot的测试类 2.加载SpringBoot启动类。启动springBoot
public class RedisTestHash {

  @Autowired
  private StringRedisTemplate redisTemplate;

  private String hashKey = "sping.boot.redis.hashmap";

  /**
   * hgetall--查询全部的fields
   */
  @Test
  public void test_entries() {
    Map<Object, Object> entries = redisTemplate.opsForHash().entries(hashKey);

    for (Object o : entries.keySet()) {
      System.out.println(o.toString());
      System.out.println(entries.get(o));
    }

  }

  /**
   * hmset--批量设置fields
   */
  @Test
  public void test_putHash() {
    Map<String, String> userNames = Maps.newConcurrentMap();
    userNames.put("lichuanjin", String.valueOf(54));
    userNames.put("luoli", String.valueOf(52));
    userNames.put("liyuanlong", String.valueOf(30));
    userNames.put("lirui", String.valueOf(28));
    userNames.put("yangzongqin", String.valueOf(28));
    redisTemplate.opsForHash().putAll(hashKey, userNames);
  }

  /**
   * hmget--批量查询fields
   */
  @Test
  public void test_getHash() {
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

  /**
   * hExists--判断是否有field
   */
  @Test
  public void test_hasKey() {
    Boolean hasField = redisTemplate.opsForHash().hasKey(hashKey, "lichuanjin");
    System.out.println(hasField);
  }

  /**
   * hVals--查询values
   */
  @Test
  public void test_getAllValue() {
    List<Object> values = redisTemplate.opsForHash().values(hashKey);

    System.out.println(values);
  }

  /**
   * hlen--查询字段的数量
   */
  @Test
  public void test_hlen() {
    Long length = redisTemplate.opsForHash().size(hashKey);
    System.out.println(length);
  }

  /**
   * HSETNX--只有在字段 field 不存在时，设置哈希表字段的值。
   */
  @Test
  public void test_putIfAbsent() {
    Boolean ifAbsent = redisTemplate.opsForHash().putIfAbsent(hashKey, "absent", 100);
    System.out.println(ifAbsent);
  }

  /**
   * hGet
   */
  @Test
  public void test_get() {
    Object o = redisTemplate.opsForHash().get(hashKey, "liyuanlong");
    System.out.println(Integer.valueOf(o.toString()));
  }

  /**
   * hIncrBy--field自增
   * <p>
   * 增量也可以为负数，相当于对指定字段进行减法操作。
   * 如果哈希表的 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
   * 如果指定的字段不存在，那么在执行命令前，字段的值被初始化为 0
   * 对一个储存字符串值的字段执行 HINCRBY 命令将造成一个错误。
   * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
   */
  @Test
  public void test_hincrby() {
    Long afterValue = redisTemplate.opsForHash().increment(hashKey, "liyuanlong", 10);
    System.out.println(afterValue);
  }

  /**
   * hdel--删除fields
   *
   * @return: 成功删除的fields数量
   */
  @Test
  public void test_hdel() {
    Long deletedCnt = redisTemplate.opsForHash().delete(hashKey, "not exist field.", "exist key.");
    System.out.println(deletedCnt);
  }

}
