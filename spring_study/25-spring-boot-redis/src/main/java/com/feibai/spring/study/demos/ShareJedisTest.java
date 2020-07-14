package com.feibai.spring.study.demos;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

public class ShareJedisTest {
  public static void main(String[] args) {
    //设置连接池的相关配置
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(2);
    poolConfig.setMaxIdle(1);
    poolConfig.setMaxWaitMillis(2000);
    poolConfig.setTestOnBorrow(false);
    poolConfig.setTestOnReturn(false);

    //设置Redis信息
    String host = "192.168.72.128";
    JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 5000);
    JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6381, 5000);


    //初始化ShardedJedisPool
    List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2);
    ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);

    //进行查询等其他操作
    ShardedJedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      jedis.set("test", "test");
      jedis.set("test1", "test1");
      jedis.set("test2", "test2");
      jedis.set("test3", "test3");
      jedis.set("test4", "test4");

      //查看具体key在哪个客户端
      Client client0 = jedis.getShard("test").getClient();
      Client client1 = jedis.getShard("test1").getClient();
      Client client2 = jedis.getShard("test2").getClient();
      Client client3 = jedis.getShard("test3").getClient();
      Client client4 = jedis.getShard("test4").getClient();
      System.out.println(client0.getHost() + ":" + client0.getPort());
      System.out.println(client1.getHost() + ":" + client1.getPort());
      System.out.println(client2.getHost() + ":" + client2.getPort());
      System.out.println(client3.getHost() + ":" + client3.getPort());
      System.out.println(client4.getHost() + ":" + client4.getPort());
    } finally {
      //使用后一定关闭，还给连接池
      if (jedis != null) {
        jedis.close();
      }
    }
  }
}