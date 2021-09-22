package com.feibai.spring.study.multithread.advanced.t08_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool_demo {

  private static ExecutorService executor = Executors.newFixedThreadPool(8);


  public static void main(String[] args) {
    new FixedThreadPool_demo().test();
  }
  public void test(){
    executor.submit(()-> {
      try {
        doJob();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  public void doJob() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(5);
    System.out.println(System.currentTimeMillis());
  }

}
