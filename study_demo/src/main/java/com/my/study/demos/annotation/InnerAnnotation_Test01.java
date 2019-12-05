package com.my.study.demos.annotation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author leeyuanlong
 *
 * @Time 2019年8月7日
 *
 */
@SuppressWarnings("all")
public class InnerAnnotation_Test01 {

	@Override
	public String toString() {
		return "";
	}

	@Deprecated
	public static void test001() {
		System.out.println("test001");
	}

	public static void test002() {
		List list = new ArrayList();
		List list2 = new ArrayList();

	}

	public static void main(String[] args) {
		Date d = new Date();
		test001();
	}

}
