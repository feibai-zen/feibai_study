package com.feibai.study.demos.multithread.advanced.t05_threadlocal;

/**
 * ThreadLocal:每个线程自身的存储本地、局部区域 get/set/initialValue
 *
 * @author feibai
 */
public class ThreadLocalTest01 {
  // private static ThreadLocal<Integer> threadLocal = new ThreadLocal<> ();
  // 更改初始化值
  /*
   * private static ThreadLocal<Integer> threadLocal = new ThreadLocal<> () {
   * protected Integer initialValue() { return 200; }; };
   */
  private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 200);

  public static void main(String[] args) {
    // 获取值
    System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
    // 设置值
    threadLocal.set(99);
    System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());

    new Thread(new MyRun()).start();
    new Thread(new MyRun()).start();
  }

  public static class MyRun implements Runnable {
    public void run() {
      System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
      threadLocal.set((int) (Math.random() * 99));
      System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
    }
  }

}
