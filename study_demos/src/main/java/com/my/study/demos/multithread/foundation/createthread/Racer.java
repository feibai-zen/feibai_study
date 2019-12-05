package com.my.study.demos.multithread.foundation.createthread;

/**
 * 
 * 
 * 模拟龟兔赛跑 视频第199集
 * 
 * 这里思考下，两个人在做 比赛，可以用两个线程分别模拟两个不同的人
 * 
 * @author leeyuanlong
 *
 */
public class Racer implements Runnable {

	private static String winner;

	@Override
	public void run() {
		for (int steps = 1; steps <= 100; steps++) {
			if (Thread.currentThread().getName() == "rabbit" && steps % 10 == 0) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "-->" + steps);
			boolean flag = gameOver(steps);
			// 判断游戏是否结束
			if (flag) {
				break;
			}
		}

	}

	private boolean gameOver(int steps) {
		if (winner != null) {
			return true;
		} else {
			if (steps == 100) {
				winner = Thread.currentThread().getName();
				System.out.println("winner==>" + winner);
			}
		}
		return false;
	}

	public static void main(String[] args) {

		Racer racer = new Racer();
		new Thread(racer, "tortoise").start();
		new Thread(racer, "rabbit").start();

	}
}
