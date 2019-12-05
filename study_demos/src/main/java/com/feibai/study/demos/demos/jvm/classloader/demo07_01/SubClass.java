package com.feibai.study.demos.demos.jvm.classloader.demo07_01;

public class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init!");
	}
}