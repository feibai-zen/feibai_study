package com.feibai.study.demos.designpatterns.singleton_my;

public class Singleton_DCL {

	private static volatile Singleton_DCL instance;

	private Singleton_DCL() {

	}

	public static Singleton_DCL getInstance() {
		if (null != instance) {
			return instance;
		}
		synchronized (Singleton_DCL.class) {

			if (null == instance) {
				instance = new Singleton_DCL();
			}

		}

		return instance;
	}

}
