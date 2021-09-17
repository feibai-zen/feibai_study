package com.feibai.study.demos.multithread.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 允许多个线程同时读共享变量；
 * 只允许一个线程写共享变量；
 * 如果一个写线程正在执行写操作，此时禁止读线程读共享变量
 */
class Cache<K, V> {
  final Map<K, V> m = new HashMap<>();
  final ReadWriteLock rwl = new ReentrantReadWriteLock();
  final Lock r = rwl.readLock();
  final Lock w = rwl.writeLock();

  V get(K key) {
    V v = null;
    //读缓存
    r.lock();         //①
    try {
      v = m.get(key); //②
    } finally {
      r.unlock();     //③
    }
    //缓存中存在，返回
    if (v != null) {  //④
      return v;
    }
    //缓存中不存在，查询数据库
    w.lock();         //⑤
    try {
      //再次验证
      //其他线程可能已经查询过数据库
      v = m.get(key);   //⑥
      if (v == null) {  //⑦
        //查询数据库
        v = null; //省略从数据源中加载数据的代码
        m.put(key, v);
      }
    } finally {
      w.unlock();
    }
    return v;
  }
}