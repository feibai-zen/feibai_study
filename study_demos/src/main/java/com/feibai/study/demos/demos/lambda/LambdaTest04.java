package com.feibai.study.demos.demos.lambda;

/**
 * lambda推导
 * 
 * @author leeyuanlong
 *
 */
public class LambdaTest04 {

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("一边学习lambda");
			}
		}).start();

		new Thread(() -> System.out.println("一边学习奔溃")).start();
	}
}