package com.feibai.study.demos.multithread.foundation.createthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 1.采用实现Runnable、Callable接口的方式创建多线程的优缺点：
 * <p>
 * 优势：
 * （1）线程类只是实现了Runnable接口与Callable接口，还可以继承其他类。
 * （2）在这种方式下，多个线程可以共享一个target对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以
 * 将CPU、代码和数据分开，形成清晰的模型，较好地体现了面向对象的思想。
 * 劣势：编程稍稍复杂，如果需要访问当前线程，则必须使用Thread.currentThread()方法。
 * <p>
 * 2.采用继承Thread类的方法创建多线程的优缺点：
 * 劣势：
 * 因为线程类已经继承了Thread类，所以不能再继承其他父类。Java中只能单继承，一旦继承了Thread类之后就不能继承其他类了，这样使用Thread方式
 * 创建线程，后期如果发现不得不继承其他的类，就难免要重构大量代码。
 * <p>
 * 优势：
 * 编写简单，如果需要访问当前线程，则无须使用Thread.currentThread()方法，直接使用this即可获得当前线程。
 *
 * @author feibai
 */
public class _ThreadTest {

  public static void main(String[] args) throws Exception {
//    test_02();
//    test_03();
    test_06();
  }

  /**
   * 继承Thread创建线程
   */
  private static void test_01() {
    Thread t1 = new ThreadExtends("t1");
    Thread t2 = new ThreadExtends("t2");
    t1.start();
    t2.start();
  }

  /**
   * 实现Runnable接口创建线程
   */
  private static void test_02() {
    //共享runnable对象
    Runnable runnable = new ThreadRunnable();
    Thread t3 = new Thread(runnable, "t3");
    Thread t4 = new Thread(runnable, "t4");
    t3.start();
    t4.start();
    t4.setPriority(Thread.MAX_PRIORITY);
    t3.getState();
  }

  /**
   * 实现Callbale接口创建线程
   */
  private static void test_03() throws Exception {
    CallableThread cd1 = new CallableThread("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
    CallableThread cd2 = new CallableThread("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
    CallableThread cd3 = new CallableThread("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
    // 创建执行服务
    ExecutorService ser = Executors.newFixedThreadPool(3);
    Future<Boolean> ret1 = ser.submit(cd1);
    Future<Boolean> ret2 = ser.submit(cd2);
    Future<Boolean> ret3 = ser.submit(cd3);
    boolean r1 = ret1.get();
    boolean r2 = ret2.get();
    boolean r3 = ret3.get();
    ser.shutdownNow();
  }

  /**
   * 多线程模拟12306购票
   */
  private static void test_04() {
    Web12306 runnableThread = new Web12306();
    Web12306 runnableThread1 = new Web12306();

    new Thread(runnableThread, "user01").start();
    new Thread(runnableThread1, "user02").start();
  }

  /**
   * 龟兔赛跑
   */
  private static void test_05() {
    TortoiseAndRabbit tortoiseAndRabbit = new TortoiseAndRabbit();
    new Thread(tortoiseAndRabbit, "tortoise").start();
    new Thread(tortoiseAndRabbit, "rabbit").start();
  }

  /**
   * 龟兔赛跑--Callable
   */
  private static void test_06() throws Exception {
    TortoiseAndRabbitCallable racer = new TortoiseAndRabbitCallable();
    // 创建执行服务:
    ExecutorService ser = Executors.newFixedThreadPool(2);
    // 提交执行:
    Future<Integer> result1 = ser.submit(racer);
    Future<Integer> result2 = ser.submit(racer);
    // 获取结果:
    Integer r1 = result1.get();
    Integer r2 = result2.get();
    System.out.println(r1 + "-->" + r2);
    // 关闭服务:
    ser.shutdownNow();
  }

}