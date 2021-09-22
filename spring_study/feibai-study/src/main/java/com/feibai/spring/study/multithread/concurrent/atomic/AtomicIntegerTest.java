package com.feibai.spring.study.multithread.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXxx 无锁同步类型
 * <p>
 * 原子操作类型。 类中的每个方法都是原子操作。可以保证线程安全。
 */
public class AtomicIntegerTest {
  AtomicInteger count = new AtomicInteger(0);

  public static void main(String[] args) {
    final AtomicIntegerTest t = new AtomicIntegerTest();
    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      threads.add(new Thread(new Runnable() {
        @Override
        public void run() {
          t.test();
        }
      }));
    }
    for (Thread thread : threads) {
      thread.start();
    }
    for (Thread thread : threads) {
      try {
        thread.join();// 其他线程都执行完成后，main线程继续执行
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(t.count.intValue());
  }

  void test() {
    for (int i = 0; i < 10000; i++) {
      /* if(count.get() < 1000) */
      count.incrementAndGet();// CAS实现的原子操作
//			count.incrementAndGet();//++stock;
//			count.getAndIncrement();//stock++;
//			count.getAndDecrement();//stock--
    }
  }
}
