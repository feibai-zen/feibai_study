package com.feibai.spring.study.test.redis;


import com.feibai.spring.study.App;
import com.feibai.spring.study.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class RedisTestObject {
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 添加Users对象
   */
  @Test
  public void testSetUesrs() {
    Users users = new Users();
    users.setAge(20);
    users.setName("张三");
    users.setId(1);
    //重新设置序列化器
    this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    this.redisTemplate.opsForValue().set("users", users);
  }

  /**
   * 取Users对象
   */
  @Test
  public void testGetUsers() {
    //重新设置序列化器
    this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    Users users = (Users) this.redisTemplate.opsForValue().get("users");
    System.out.println(users);
  }

  /**
   * 基于JSON格式存Users对象
   */
  @Test
  public void testSetUsersUseJSON() {
    Users users = new Users();
    users.setAge(20);
    users.setName("李四");
    users.setId(1);
    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
    this.redisTemplate.opsForValue().set("get ", users);
  }

  /**
   * 基于JSON格式取Users对象
   */
  @Test
  public void testGetUseJSON() {
    this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Users.class));
    Users users = (Users) this.redisTemplate.opsForValue().get("users_json");
    System.out.println(users);
  }

}
