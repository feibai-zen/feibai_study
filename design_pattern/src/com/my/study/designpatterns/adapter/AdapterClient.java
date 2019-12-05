package com.my.study.designpatterns.adapter;

/**
 * 客户端类 (相当于例子中的笔记本，只有USB接口)
 * 
 * @author leeyuanlong
 *
 */
public class AdapterClient {

	public void trans(Target t) {
		t.translateData();
	}

	public static void main(String[] args) {
		AdapterClient adapterClient = new AdapterClient();

		Target t = new Adapter();
		adapterClient.trans(t);

		Adaptee a = new Adaptee();
		Target t2 = new Adapter2(a);
		adapterClient.trans(t2);

	}

}
