package com.feibai.study.demos.designpatterns.singleton_my;

/**
 * 单例模式---懒汉式
 * 
 * @author feibai
 *
 * @Time 2019年8月12日
 *
 */
public class Singleton_Lazy {

	private static Singleton_Lazy instance;

	private Singleton_Lazy() {

	}

	public static synchronized Singleton_Lazy getInstance() {

		if (null == instance) {
			instance = new Singleton_Lazy();
		}
		return instance;
	}

	public static void main(String[] args) {
		System.out.println(getInstance());
		System.out.println(getInstance());
		System.out.println(getInstance());
		System.out.println(getInstance());
	}

}
