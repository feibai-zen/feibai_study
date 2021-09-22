package com.feibai.spring.study.multithread.advanced.t08_threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

  public static void main(String[] args) {
    List<String> subTaskList = new ArrayList<String>();
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 30, 60, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());
    final List<String> listDataSize = Collections.synchronizedList(new ArrayList<String>());
    for (String oneTask : subTaskList) {
      threadPool.execute(new Runnable() {
        @Override
        public void run() {
          listDataSize.add(doTask(oneTask));// 只是做一个测试
        }
      });
    }
    threadPool.shutdown();// 不能再提交任务，但是不影响待执行的任务

    try {
      boolean isContinue = true;
      do {
        isContinue = !threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
      } while (isContinue);
    } catch (Exception e) {

    }
  }

  private static String doTask(String oneTask) {
    System.out.println("I am a task.");
    return oneTask;
  }
}

/**
 * corePoolSize： 线程池维护线程的最少数量
 * <p>
 * maximumPoolSize：线程池维护线程的最大数量
 * <p>
 * keepAliveTime： 线程池维护线程所允许的空闲时间
 * <p>
 * unit： 线程池维护线程所允许的空闲时间的单位
 * <p>
 * workQueue： 线程池所使用的缓冲队列
 * <p>
 * handler： 线程池对拒绝任务的处理策略
 * <p>
 * <p>
 * <p>
 * 一个任务通过 execute(Runnable)方法被添加到线程池，任务就是一个
 * Runnable类型的对象，任务的执行方法就是Runnable类型对象的run() 方法。
 * <p>
 * <p>
 * <p>
 * 当一个任务通过execute(Runnable)方法欲添加到线程池时：
 * <p>
 * l 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
 * l 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 * l 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添
 * 加的任务。
 * l 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过
 * handler所
 * 指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如
 * 果三 者都满了，使用handler处理被拒绝的任务。
 * <p>
 * l 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的
 * 线程数。
 * <p>
 * <p>
 * Handler有四个选择：
 * <p>
 * 1)ThreadPoolExecutor.AbortPolicy()
 * 抛出java.util.concurrent.RejectedExecutionException异常
 * <p>
 * 2)ThreadPoolExecutor.CallerRunsPolicy()
 * <p>
 * 当抛出RejectedExecutionException异常时，会调用rejectedExecution方法
 * <p>
 * (如果主线程没有关闭，则主线程调用run方法,源码如下
 * <p>
 * public void rejectedExecution(Runnable r, ThreadPoolExecutor e) { if
 * (!e.isShutdown()) { r.run(); } }
 * <p>
 * )
 * <p>
 * <p>
 * 3)抛弃旧的任务
 * ThreadPoolExecutor.DiscardOldestPolicy()
 * <p>
 * 4)抛弃当前的任务
 * ThreadPoolExecutor.DiscardPolicy()
 */
