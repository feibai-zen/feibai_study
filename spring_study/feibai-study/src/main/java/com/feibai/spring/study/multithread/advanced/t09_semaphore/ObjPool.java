package com.feibai.spring.study.multithread.advanced.t09_semaphore;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

public class ObjPool<T, R> {
  private List<T> pool;
  //使用信号量实现限流器
  private final Semaphore semaphore;


  ObjPool(T[] tArray) {
    pool = new Vector<T>() {
    };
    int size = tArray.length;
    for (int i = 0; i < size; i++) {
      pool.add(tArray[i]);
    }
    semaphore = new Semaphore(size);
  }

  R exec(Function<T, R> func) throws InterruptedException {
    T t = null;
    semaphore.acquire();
    try {
      t = pool.remove(0);
      return func.apply(t);
    } finally {
      pool.add(t);
      semaphore.release();
    }
  }

  public static void main(String[] args) {
    String[] messages = new String[10];
    for (int i = 0; i < 10; i++) {
      messages[i] = "obj_" + i;
    }
    ObjPool<String, String> pool = new ObjPool<>(messages);

    for (int i = 0; i < 100; i++) {
      Thread thread = new Thread(() -> {
        try {
          pool.exec(t -> {
            System.out.println("当前线程id:" + Thread.currentThread().getId() + ",当前获取到的对象：" + t);
            return t;
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }


}