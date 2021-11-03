package com.feibai.study.demos.jvm.lock;

public class SynchronizedDemo {
  public void method(){
    synchronized(this){
      System.out.println("test to see synchronized block.");
    }
  }

  public synchronized void method1(){
    System.out.println("test to see synchronized method.");
  }
}
