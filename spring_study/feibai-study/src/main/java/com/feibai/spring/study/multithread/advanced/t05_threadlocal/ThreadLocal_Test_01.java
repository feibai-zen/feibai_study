package com.feibai.spring.study.multithread.advanced.t05_threadlocal;

/**
 * ThreadLocal
 * <p>
 * 作用：多线程环境为每个线程保存变量
 * <p>
 * 就是一个Map。 key ->Thread.getCurrentThread() value ->线程需要保存的变量。
 * <p>
 * ThreadLocal.set(value) -> map.put(Thread.getCurrentThread(), value);
 * <p>
 * ThreadLocal.get() -> map.get(Thread.getCurrentThread());
 * <p>
 * 内存问题 ： 在并发量高的时候，可能有内存溢出。
 * <p>
 * 使用ThreadLocal的时候，一定注意回收资源问题，每个线程结束之前，将当前线程保存的线程变量一定要删除 。
 * <p>
 * ThreadLocal.remove();
 * <p>
 * 补充知识点：
 * <p>
 * 在操作系统中，线程和进程有数量上限，确认线程和进程唯一性的唯一条件就是线程ID或进程ID。
 * 操作系统在回收线程或进程的时候，不是一定杀死进程或者线程，在繁忙的时候，只会做清空线程或进程的操作，重复使用线程或进程
 */

import java.util.concurrent.TimeUnit;

public class ThreadLocal_Test_01 {

  volatile static String name = "zhangsan";
  static ThreadLocal<String> tl = new ThreadLocal<>();

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(name);
        System.out.println(tl.get());
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        name = "lisi";
        tl.set("wangwu");
      }
    }).start();
  }

}
