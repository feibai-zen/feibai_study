package com.feibai.study.demos.multithread.advanced.t01_review;

/**
 * 
 * @author feibai
 *
 */
public class SynchronizedFetchMoney {

	public static void main(String[] args) {
		// 账户
		Account account = new Account(1000, "结婚礼金");
		SynDrawing you = new SynDrawing(account, 80, "可悲的你");
		SynDrawing wife = new SynDrawing(account, 90, "happy的她");
		you.start();
		wife.start();
	}
}

// 模拟取款 线程安全
class SynDrawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney;// 取的钱数
	int packetTotal; // 口袋的总数

	public SynDrawing(Account account, int drawingMoney, String name) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
		test();
	}

	// 目标锁定account
	public void test() {
		// 提高性能
		if (account.money <= 0) {
			return;
		}
		// 同步块
		synchronized (account) {
			if (account.money - drawingMoney < 0) {
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.money -= drawingMoney;
			packetTotal += drawingMoney;
			System.out.println(this.getName() + "-->账户余额为:" + account.money);
			System.out.println(this.getName() + "-->口袋的钱为:" + packetTotal);
		}
	}
}

class Account {
	int money; // 金额
	String name; // 名称

	public Account(int money, String name) {
		this.money = money;
		this.name = name;
	}
}