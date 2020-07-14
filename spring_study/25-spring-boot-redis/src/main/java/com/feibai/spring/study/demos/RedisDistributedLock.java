package com.feibai.spring.study.demos;

import java.util.List;
import java.util.UUID;

import com.feibai.spring.study.common.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

public class RedisDistributedLock {
  private final JedisPool jedisPool;

  public RedisDistributedLock(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  /**
   * 获取分布式锁
   *
   * @param lockName           竞争获取锁key
   * @param acquireTimeoutInMS 获取锁超时时间
   * @param lockTimeoutInMS    锁的超时时间
   * @return 获取锁标识
   */
  public String acquireLockWithTimeout(String lockName,
                                       long acquireTimeoutInMS, long lockTimeoutInMS) {
    Jedis conn = null;
    boolean broken = false;
    String retIdentifier = null;

    try {
      conn = jedisPool.getResource();
      String identifier = UUID.randomUUID().toString();
      String lockKey = "lock:" + lockName;
      int lockExpire = (int) (lockTimeoutInMS / 1000);

      long end = System.currentTimeMillis() + acquireTimeoutInMS;
      while (System.currentTimeMillis() < end) {
        if (conn.setnx(lockKey, identifier) == 1) {//不存在这个key，则拿到锁
          conn.expire(lockKey, lockExpire);
          retIdentifier = identifier;
        }
        if (conn.ttl(lockKey) == -1) {//没有过期时间就重新设置过期时间
          conn.expire(lockKey, lockExpire);
        }
        try {
          Thread.sleep(10);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
      }
    } catch (JedisException je) {
      if (conn != null) {
        broken = true;
        conn.close();
//                jedisPool.returnBrokenResource(conn);
      }
    } finally {
      if (conn != null && !broken) {
//                jedisPool.returnResource(conn);
        conn.close();
      }
    }
    return retIdentifier;
  }

  /**
   * 释放锁
   *
   * @param lockName   竞争获取锁key
   * @param identifier 释放锁标识
   * @return
   */
  public boolean releaseLock(String lockName, String identifier) {
    Jedis conn = null;
    boolean broken = false;
    String lockKey = "lock:" + lockName;
    boolean retFlag = false;
    try {
      conn = jedisPool.getResource();
      while (true) {
        conn.watch(lockKey);
        if (identifier.equals(conn.get(lockKey))) {
          Transaction trans = conn.multi();
          trans.del(lockKey);
          List<Object> results = trans.exec();
          if (results == null) {
            continue;
          }
          retFlag = true;
        }
        conn.unwatch();
        break;
      }

    } catch (JedisException je) {
      je.printStackTrace();
      if (conn != null) {
        broken = true;
        conn.close();
      }
    } finally {
      if (conn != null && !broken) {
        conn.close();
      }
    }
    return retFlag;
  }

  public static void main(String[] args) {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(20);
//        jedisPoolConfig.setMaxIdle(10);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.72.128", 6379);
    JedisPool jedisPool = JedisPoolUtils.getInstance();
    RedisDistributedLock lock = new RedisDistributedLock(jedisPool);
    String result = lock.acquireLockWithTimeout("aaa", 11111, 13331);
    System.out.println("lock:" + result);
    System.out.println(lock.releaseLock("aaa", result));
  }

}
