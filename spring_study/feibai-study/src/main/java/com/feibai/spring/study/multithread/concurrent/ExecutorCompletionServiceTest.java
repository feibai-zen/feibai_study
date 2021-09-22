package com.feibai.spring.study.multithread.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class ExecutorCompletionServiceTest {

  /**
   * 询价系统：向3家电商询价，并保存
   */
  @Test
  public void test1() throws Exception {
    // 创建线程池
    ExecutorService executor = Executors.newFixedThreadPool(3);
    // 创建CompletionService
    CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
    // 异步向电商S1询价
    cs.submit(this::getPriceByS1);
    // 异步向电商S2询价
    cs.submit(this::getPriceByS2);
    // 异步向电商S3询价
    cs.submit(this::getPriceByS3);
    // 将询价结果异步保存到数据库
    for (int i = 0; i < 3; i++) {
      Integer r = cs.take().get();
      executor.execute(() -> save(r));
    }
  }

  /**
   * 向3家电商询价，保存并计算最低报价
   *
   * 问题1：线程安全问题
   * 问题2：查询Comple
   */
  @Test
  public void test2() {
    // 创建线程池
    ExecutorService executor = Executors.newFixedThreadPool(3);
    // 创建CompletionService
    CompletionService<Integer> cs = new
        ExecutorCompletionService<>(executor);
    // 异步向电商S1询价
    cs.submit(() -> getPriceByS1());
    // 异步向电商S2询价
    cs.submit(() -> getPriceByS2());
    // 异步向电商S3询价
    cs.submit(() -> getPriceByS3());
    // 将询价结果异步保存到数据库
    // 并计算最低报价
    AtomicReference<Integer> m = new AtomicReference<>(Integer.MAX_VALUE);
    for (int i = 0; i < 3; i++) {
      executor.execute(() -> {
        Integer r = null;
        try {
          r = cs.take().get();
        } catch (Exception e) {
        }
        save(r);
        //这里有线程安全问题
        m.set(Integer.min(m.get(), r));
      });
    }
    //这里有问题：异步线程从CompletionService中查询结果的线程不会阻塞主线程。输出的m是设置的初始值
    System.out.println(m);
  }

  /**
   * 修改 test2的问题
   */
  @Test
  public void test3() throws InterruptedException {
    // 创建线程池
    ExecutorService executor = Executors.newFixedThreadPool(3);
    // 创建CompletionService
    CompletionService<Integer> cs = new
        ExecutorCompletionService<>(executor);
    // 异步向电商S1询价
    cs.submit(() -> getPriceByS1());
    // 异步向电商S2询价
    cs.submit(() -> getPriceByS2());
    // 异步向电商S3询价
    cs.submit(() -> getPriceByS3());
    // 将询价结果异步保存到数据库
    // 并计算最低报价
    CountDownLatch latch = new CountDownLatch(3);
    AtomicReference<Integer> m = new AtomicReference<>(Integer.MAX_VALUE);
    for (int i = 0; i < 3; i++) {
      executor.execute(() -> {
        Integer r = null;
        try {
          r = cs.take().get();
          latch.countDown();
        } catch (Exception e) {
        }
        save(r);
        m.set(Integer.min(m.get(), r));
      });
    }
    latch.await();
    System.out.println(m);
  }

  /**
   * 3个线程同时查询地理位置，任何一个线程查询到结果后就返回，同时取消其他线程的任务
   * <p>
   * 1) 创建了一个线程池 executor 、一个 CompletionService 对象 cs 和一个Future类型的列表 futures
   * 2) 每次通过调用 CompletionService 的 submit() 方法提交一个异步任务，会返回一个 Future 对象，
   * 3) 把这些 Future 对象保存在列表 futures 中。通过调用 cs.take().get()，能够拿到最快返回的任务执
   * 行结果，只要拿到一个正确返回的结果，就可以取消所有任务并且返回最终结果
   */
  @Test
  public void test4() throws InterruptedException {
    // 创建线程池
    ExecutorService executor =
        Executors.newFixedThreadPool(3);
    // 创建CompletionService
    CompletionService<Integer> cs =
        new ExecutorCompletionService<>(executor);
    // 用于保存Future对象
    List<Future<Integer>> futures = new ArrayList<>(3);
    //提交异步任务，并保存future到futures
    futures.add(cs.submit(() -> geocoderByS1()));
    futures.add(cs.submit(() -> geocoderByS2()));
    futures.add(cs.submit(() -> geocoderByS3()));
    // 获取最快返回的任务执行结果
    Integer r = 0;
    try {
      // 只要有一个成功返回，则break
      for (int i = 0; i < 3; ++i) {
        r = cs.take().get();
        //简单地通过判空来检查是否成功返回
        if (r != null) {
          break;
        }
      }
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      //取消所有任务
      for (Future<Integer> f : futures)
        f.cancel(true);
    }
    // 返回结果
    System.out.println(r);
  }

  private void save(Integer pa) {
    System.out.println("save result " + pa);
  }

  private Integer getPriceByS1() throws InterruptedException {
    Thread.sleep(1000);
    return 1;
  }

  private Integer getPriceByS2() throws InterruptedException {
    Thread.sleep(600);
    return 2;
  }

  private Integer getPriceByS3() throws InterruptedException {
    Thread.sleep(500);
    return 3;
  }

  private Integer geocoderByS1() {
    return 1;
  }

  private Integer geocoderByS2() {
    return 2;
  }

  private Integer geocoderByS3() {
    return 3;
  }
}
