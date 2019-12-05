package com.feibai.study.demos.demos.jvm.classloader.demo_evernote;

class Parent {
	static String name = "hello";
	{
		System.out.println("parent block");
	}
	static {
		System.out.println("parent static block");
	}

	public Parent() {
		System.out.println("parent constructor");
	}
}

class Child extends Parent {
	static String childName = "hello";
	{
		System.out.println("child block");
	}
	static {
		System.out.println("child static block");
	}

	public Child() {
		System.out.println("child constructor");
	}
}

public class TestStatic {

	public static void main(String[] args) {
		new Child();// 语句(*)
	}
}