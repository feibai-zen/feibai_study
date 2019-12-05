package com.my.study.designpatterns.proxy.proxystatic;

public class SingerProxyStatic {

	// client
	public static void main(String[] args) {
		Star real = new Jay();
		Star proxy = new ProxyStar(real);

		proxy.confer();
		proxy.signContract();
		proxy.bookTicket();
		proxy.sing();
		proxy.collectMoney();

	}
}

class Jay implements Star {

	@Override
	public void bookTicket() {
		System.out.println("RealStar.bookTicket()");
	}

	@Override
	public void collectMoney() {
		System.out.println("RealStar.collectMoney()");
	}

	@Override
	public void confer() {
		System.out.println("RealStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("RealStar.signContract()");
	}

	@Override
	public void sing() {
		System.out.println("RealStar(周杰伦本人).sing()");
	}

}

class ProxyStar implements Star {

	private Star star;

	public ProxyStar(Star star) {
		super();
		this.star = star;
	}

	@Override
	public void bookTicket() {
		System.out.println("ProxyStar.bookTicket()");
	}

	@Override
	public void collectMoney() {
		System.out.println("ProxyStar.collectMoney()");
	}

	@Override
	public void confer() {
		System.out.println("ProxyStar.confer()");
	}

	@Override
	public void signContract() {
		System.out.println("ProxyStar.signContract()");
	}

	@Override
	public void sing() {
		star.sing();
	}

}

interface Star {
	/**
	 * 面谈
	 */
	void confer();

	/**
	 * 签合同
	 */
	void signContract();

	/**
	 * 订票
	 */
	void bookTicket();

	/**
	 * 唱歌
	 */
	void sing();

	/**
	 * 收钱
	 */
	void collectMoney();
}