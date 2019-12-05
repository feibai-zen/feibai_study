package com.feibai.study.demos.designpatterns.adapter;

/**
 * 被适配的类 (相当于例子中的，PS/2键盘)
 * 
 * @author leeyuanlong
 *
 */
public class Adaptee {

	public void communicate() {
		System.out.println("可以完成与主机通信目的...");
	}
}