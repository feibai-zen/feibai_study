package com.feibai.study.demos.multithread.advanced.t05_threadlocal;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 利用ThreadLocal实现：线程安全的SimpleDateFormat
 * <p>
 * 1.每创建一个thread，就对应创建一个新的simpleDateFormat对象
 * 2.同一个线程多次获取simpleDateFormat对象时，只会获取同一个simpleDateFormat对象
 *
 * 现在出现点问题：多个线程中获取到的对象居然是同一个对象，问题出在哪里呢？
 */
public class ThreadLocal_SafeDateFormat {
  //定义ThreadLocal变量
  static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(
      () -> {
        System.out.println(Thread.currentThread().getName() + "-->" + "withInitial....");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      }
  );

  static DateFormat get() {
    return tl.get();
  }

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + "-->" + ThreadLocal_SafeDateFormat.get());

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "-->" + ThreadLocal_SafeDateFormat.get());
    }, "son thread1").start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + "-->" + ThreadLocal_SafeDateFormat.get());
    }, "son thread2").start();
  }

}
