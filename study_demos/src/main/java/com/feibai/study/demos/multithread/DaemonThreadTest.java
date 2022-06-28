package com.feibai.study.demos.multithread;

public class DaemonThreadTest {

  public static void main(String[] args) {
    ThreadDaemon daemonThread = new ThreadDaemon();

    Thread daemon = new Thread(daemonThread);
    daemon.setDaemon(true);
    daemon.start();
    new Thread(new ThreadNormal()).start();
  }

}

class ThreadDaemon implements Runnable {
  @Override
  public void run() {
    while (true)
      try {
        Thread.sleep(200);
        System.out.println("this is daemon thread.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
  }
}

class ThreadNormal implements Runnable {
  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        Thread.sleep(200);
        System.out.println("this is normal thread.");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }

}