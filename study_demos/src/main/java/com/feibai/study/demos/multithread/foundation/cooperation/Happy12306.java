package com.feibai.study.demos.multithread.foundation.cooperation;

/**
 * 快乐火车票
 *
 * @author feibai
 */
public class Happy12306 {

  public static void main(String[] args) {
    Web12306 c = new Web12306(4, "happy sxt");
    new Thread(new Passenger(c, "老高", 2)).start();
    new Thread(new Passenger(c, "老裴", 1)).start();
  }

}

//顾客
class Passenger implements Runnable {
  int seats;
  Web12306 target;
  String name;

  public Passenger(Web12306 target, String name, int seats) {
    this.seats = seats;
    this.target = target;
    this.name = name;
  }

  @Override
  public void run() {
    target.bookTickets(seats);
  }
}

//火车票网
class Web12306 {
  int available; //可用的位置
  String name; //名称

  public Web12306(int available, String name) {
    this.available = available;
    this.name = name;
  }

  //购票
  public synchronized boolean bookTickets(int seats) {
    System.out.println("可用位置为:" + available);
    if (seats > available) {
      System.out.println("出票失败" + Thread.currentThread().getName() + "-<位置不够");
      return false;
    }
    available -= seats;
    System.out.println("出票成功" + Thread.currentThread().getName() + "-<位置为:" + seats);
    return true;
  }
}