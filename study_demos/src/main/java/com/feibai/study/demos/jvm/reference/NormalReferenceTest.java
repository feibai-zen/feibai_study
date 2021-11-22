package com.feibai.study.demos.jvm.reference;

import java.io.IOException;

/**
 * 强引用
 *
 * 测试强引用
 *
 * 总结引用: 他强任他强，缓存软引挤一旁。弱引若无哈希key，内存漏洞全补上，虚幻似鬼魅，队列来帮忙，身形缥缈何处觅？生死此中藏。
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
