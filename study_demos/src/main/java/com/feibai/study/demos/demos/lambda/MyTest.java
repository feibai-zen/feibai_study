package com.feibai.study.demos.demos.lambda;

/**
 * 
 * 使用lambda需要先定义一个接口
 * 
 * @author leeyuanlong
 *
 */
public class MyTest {
	public static void main(String[] args) {
		INeed ineed = () -> {
			System.out.println("i like lambda5 ");
		};
		ineed.tosay();
	}
}

interface INeed {
	void tosay();
}
