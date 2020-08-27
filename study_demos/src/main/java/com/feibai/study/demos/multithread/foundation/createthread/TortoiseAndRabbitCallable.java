package com.feibai.study.demos.multithread.foundation.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 模拟龟兔赛跑
 *
 * @author feibai
 */
public class TortoiseAndRabbitCallable implements Callable<Integer> {

  private String winner;// 胜利者

  @Override
  public Integer call() throws Exception {
    for (int steps = 1; steps <= 100; steps++) {
      // 模拟休息
      if (Thread.currentThread().getName().equals("pool-1-thread-1") && steps % 10 == 0) {
        Thread.sleep(100);
      }
      System.out.println(Thread.currentThread().getName() + "-->" + steps);
      // 比赛是否结束
      boolean flag = gameOver(steps);
      if (flag) {
        return steps;
      }
    }
    return null;
  }

  private boolean gameOver(int steps) {
    if (winner != null) { // 存在胜利者
      return true;
    } else {
      if (steps == 100) {
        winner = Thread.currentThread().getName();
        System.out.println("winner==>" + winner);
        return true;
      }
    }
    return false;
  }
}
