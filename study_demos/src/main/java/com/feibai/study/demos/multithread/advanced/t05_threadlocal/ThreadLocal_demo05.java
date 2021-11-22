package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

/**
 * InheritableThreadLocal
 * 继承上下文环境的数据 ，拷贝一份给子线程，之后子线程修改数据之后对主线程没有影响
 *
 * @author feibai
 */
public class ThreadLocal_demo05 {
  private static ThreadLocal<Integer> inheritableTl = new InheritableThreadLocal<>();

  public static void main(String[] args) throws InterruptedException {
    inheritableTl.set(2);
    System.out.println(Thread.currentThread().getName() + "-->" + inheritableTl.get());

    // 线程由main线程开辟
    Thread t = new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "-->" + inheritableTl.get());
      inheritableTl.set(200);
      System.out.println(Thread.currentThread().getName() + "-->" + inheritableTl.get());
    });

    t.start();
    t.join();

    System.out.println(Thread.currentThread().getName() + "-->" + inheritableTl.get());
  }

}
