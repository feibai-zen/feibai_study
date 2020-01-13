package com.feibai.study.demos.designpatterns.strategy;

/**
 * @author feibai
 */
public class Context {
	private Strategy strategy; // 当前采用的算法对象

	// 可以通过构造器来注入
	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	// 可以通过set方法来注入
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public void printPrice(double s) {
		System.out.println("您该报价：" + strategy.getPrice(s));
	}

}
