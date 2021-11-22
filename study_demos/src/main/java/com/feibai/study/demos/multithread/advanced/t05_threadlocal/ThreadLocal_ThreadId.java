package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 为每个线程分配一个唯一ID
 */
public class ThreadLocal_ThreadId {

  static final AtomicLong nextId = new AtomicLong(0);
  //定义ThreadLocal变量
  static final ThreadLocal<Long> tl = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

  //此方法会为每个线程分配一个唯一的Id
  static long get() {
    return tl.get();
  }

  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "-->" + ThreadLocal_ThreadId.get());
    }, "son thread").start();

    System.out.println(Thread.currentThread().getName() + "-->" + ThreadLocal_ThreadId.get());
  }

}