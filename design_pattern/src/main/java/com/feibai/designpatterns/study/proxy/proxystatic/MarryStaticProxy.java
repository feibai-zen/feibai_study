package com.feibai.designpatterns.study.proxy.proxystatic;

/**
 * 使用的场景，一项任务，需要交给谁去做。为我办事，我是主体，代理负责周边辅助性工作。
 * 比如结婚：结婚的人是我，我可以找婚庆公司做代理。我和婚庆公司怎么联系起来呢？靠的是
 * 结婚这个事情，所以接口是结婚这个动作。代理要代理什么动作，就继承什么接口。
 * 
 * 抽象的：结婚的人是一个群体，视为核心对象，做的动作是核心动作。 代理处理的是通用的事
 * 情，代理角色能力强大之后，也可能反客为主，成为重要的角色。
 *
 *
 * 核心角色注入的方式1）构造方法  2）spring依赖注入
 * 
 * @author feibai
 *
 */
public class MarryStaticProxy {

	public static void main(String[] args) {
		MarryCompany company = new MarryCompany(new You());
		company.marry();
	}
}

interface Marry {
	void marry();
}

class You extends Person {
	@Override
	public void marry() {
		System.out.println("I am marring...");
	}

}

class Person implements Marry {
	@Override
	public void marry() {
	}
}

class MarryCompany implements Marry {
	private Person marryTarget;

	public MarryCompany(Person marryTarget) {
		this.marryTarget = marryTarget;
	}

	@Override
	public void marry() {
		prepareMarry();
		marryTarget.marry();
		endingMarry();
	}

	private void prepareMarry() {
		System.out.println("婚礼前准备....");
	}

	private void endingMarry() {
		System.out.println("婚礼后准备....");
	}

}