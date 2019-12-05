package com.feibai.study.demos.designpatterns.singleton;

/**
 * DCL单例模式: 懒汉式套路基础上加入并发控制，保证在多线程环境下，对外存在一个对象 1、构造器私有化 -->避免外部new构造器 2、提供私有的静态属性
 * -->存储对象的地址 3、提供公共的静态方法 --> 获取属性
 * 
 * @author leeyuanlong
 *
 */
public class SingletonDoubleCheckedLock {
	// 2、提供私有的静态属性
	// 没有volatile其他线程可能访问一个没有初始化的对象
	private static volatile SingletonDoubleCheckedLock instance;

	// 1、构造器私有化
	private SingletonDoubleCheckedLock() {
	}

	// 3、提供公共的静态方法 --> 获取属性
	public static SingletonDoubleCheckedLock getInstance() {
		// 再次检测
		if (null != instance) { // 避免不必要的同步 ，已经存在对象
			return instance;
		}
		synchronized (SingletonDoubleCheckedLock.class) {// getInstance()是静态方法
			if (null == instance) {
				instance = new SingletonDoubleCheckedLock();
				// 1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
			}
		}
		return instance;
	}

	public static SingletonDoubleCheckedLock getInstance1(long time) {
		if (null == instance) {
			try {// 第一个线程处于休眠状态的时候，第二个线程进来了
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new SingletonDoubleCheckedLock();
			// 1、开辟空间 //2、初始化对象信息 //3、返回对象的地址给引用
		}
		return instance;
	}

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			System.out.println(SingletonDoubleCheckedLock.getInstance());
		});
		t.start();
		System.out.println(SingletonDoubleCheckedLock.getInstance());
	}

}
