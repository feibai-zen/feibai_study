package com.feibai.study.demos.multithread.advanced.t09_semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountLimit {

  public static void main(String[] args) throws InterruptedException {
    // 线程池
    ExecutorService exec = Executors.newCachedThreadPool();
    // 只能5个线程同时访问
    final Semaphore semp = new Semaphore(5);
    // 模拟20个客户端访问
    for (int index = 0; index < 20; index++) {
      final int NO = index;
      Runnable run = new Runnable() {
        public void run() {
          try {
            // 获取许可
            semp.acquire();
            System.out.println("Accessing: " + NO);
            //模拟实际业务逻辑
            Thread.sleep((long) (Math.random() * 10000));
          } catch (Exception e) {
            // 访问完后，释放
          } finally {
            semp.release();
          }
        }
      };
      exec.submit(run);
    }
    exec.shutdown();
    boolean isDone = false;
    while(!isDone){
      isDone = exec.awaitTermination(100, TimeUnit.MILLISECONDS);
    }
  }
}


