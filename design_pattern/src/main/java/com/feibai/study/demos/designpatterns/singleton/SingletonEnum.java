package com.feibai.study.demos.designpatterns.singleton;

/**
 * 枚举方式实现单例模式
 * 
 * 优点：1.实现简单 2.枚举本身就是单例模式。由JVM从根本上提供保障，避免通过反射和反序列化的漏洞。
 * 
 * 缺点：无法延迟加载
 * 
 * @author leeyuanlong
 *
 */
public enum SingletonEnum {

	INSTANCE;

	// 添加其他方法...
	public void init() {
	}

	public static void main(String[] args) {
		SingletonEnum instancEnum = SingletonEnum.INSTANCE;
		SingletonEnum instancEnum1 = SingletonEnum.INSTANCE;
		SingletonEnum instancEnum2 = SingletonEnum.INSTANCE;
		System.out.println(instancEnum == instancEnum1);
		System.out.println(instancEnum1 == instancEnum2);

	}

}
