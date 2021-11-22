package com.feibai.study.demos.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试虚引用PhantomReference
 *
 * 虚引用使用队列可以跟踪对象释放过程。
 * 比如在NIO的实现中，JVM GC无法释放申请的直接内存，这时候需要跟踪ReferenceQueue中对象，调用C++的代码对直接内存进行释放。
 *
 * <p>
 * -Xmx20m
 */
public class PhantomReferenceTest {
  private static final List<Object> LIST = new ArrayList<>();
  private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

  public static void main(String[] args) {
    PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
    //无法通过虚引用来取得一个对象实例
    System.out.println(phantomReference.get());

    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//分配在JVM堆内存上
    ByteBuffer byteBufferDirect = ByteBuffer.allocateDirect(1024 * 1024);//分配直接内存,Zero Copy
    new Thread(() -> {
      while (true) {
        LIST.add(new byte[1024 * 1024]);
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(phantomReference.get());
      }
    }).start();

    new Thread(() -> {
      while (true) {
        Reference<? extends M> reference = QUEUE.poll();
        if (reference != null) {
          System.out.println("----虚引用被回收了----");
        }
      }
    }).start();

  }
}
