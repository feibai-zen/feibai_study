package com.feibai.study.demos.demos.reflect;

import java.lang.reflect.Method;

/**
 * 通过跳过安全检查，提高反射效率 三种执行方法的效率差异比较
 * 
 * @author feibai
 *
 * @Time 2019年8月7日
 *
 */
public class EfficientCompare {

	public static void test01() {
		User u = new User();

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000000L; i++) {
			u.getUname();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("普通方法调用，执行10亿次，耗时：" + (endTime - startTime) + "ms");
	}

	public static void test02() throws Exception {
		User u = new User();
		Class clazz = u.getClass();
		Method m = clazz.getDeclaredMethod("getUname", null);
//		m.setAccessible(true);

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000000L; i++) {
			m.invoke(u, null);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("反射动态方法调用，执行10亿次，耗时：" + (endTime - startTime) + "ms");
	}

	public static void test03() throws Exception {
		User u = new User();
		Class clazz = u.getClass();
		Method m = clazz.getDeclaredMethod("getUname", null);
		m.setAccessible(true); // 不需要执行访问安全检查

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000000L; i++) {
			m.invoke(u, null);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("反射动态方法调用，跳过安全检查，执行10亿次，耗时：" + (endTime - startTime) + "ms");
	}

	public static void main(String[] args) throws Exception {
		test01();
		test02();
		test03();
	}
}