package com.feibai.study.demos.designpatterns.singleton_my;

/**
 * 单例模式--饿汉式
 * 
 * @author feibai
 *
 * @Time 2019年8月12日
 *
 */
public class Singleton_Hungry {

	private static Singleton_Hungry instance = new Singleton_Hungry();

	private Singleton_Hungry() {
		if (instance != null) {
			throw new RuntimeException("use singleton, can't create another instance.");
		}
	}

	public static Singleton_Hungry getInstance() {
		return instance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
