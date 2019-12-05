package com.my.study.designpatterns.singleton_my;

public class Singleton_InnerStaticClass {

	private static class InnerClass {
		private static Singleton_InnerStaticClass instance = new Singleton_InnerStaticClass();
	}

	private Singleton_InnerStaticClass() {
	}

	public static Singleton_InnerStaticClass getInstance() {
		return InnerClass.instance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
