package com.feibai.study.demos.multithread.schedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度: Timer和TimerTask类，定时启动一个线程
 *
 * @author feibai
 */
public class TimerScheduler {

  public static void main(String[] args) {
    Timer timer = new Timer();
    // 执行安排
    // timer.schedule(new MyTask(), 1000); //执行任务一次
    // timer.schedule(new MyTask(), 1000,200); //执行多次
    Calendar cal = new GregorianCalendar(2019, 12, 31, 21, 53, 54);
    timer.schedule(new MyTask(), cal.getTime(), 2000); // 指定时间
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println("放空大脑休息一会");
        }
        System.out.println("------end-------");
      }
    }, 100, 200);
  }

}

//任务类
class MyTask extends TimerTask {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("放空大脑休息一会");
    }
    System.out.println("------end-------");
  }

}
