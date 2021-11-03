package com.feibai.study.demos.jvm.reference;

import java.io.IOException;

/**
 * 强引用
 *
 * 测试强引用
 */
public class NormalReferenceTest {
  public static void main(String[] args) throws IOException {
    M m = new M();
    m = null;
    System.gc();// 1）GC线程是单独线程，不会阻塞主线程；2）也可以通过disableExplictGC参数禁用显式GC
    System.out.println(m);
    System.in.read(); // 阻塞主线程
  }
}

class M {
  @Override
  protected void finalize(){
    System.out.println("class M finalize() method.");
  }
}
