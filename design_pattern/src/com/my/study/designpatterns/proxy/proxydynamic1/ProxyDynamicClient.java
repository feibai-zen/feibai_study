package com.my.study.designpatterns.proxy.proxydynamic1;

import java.lang.reflect.Proxy;

public class ProxyDynamicClient {
	public static void main(String[] args) {

		Star realStar = new Jay();
		StarHandler handler = new StarHandler(realStar);

		Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Star.class },
				handler);

		proxy.bookTicket();
		System.out.println("=============================");
		proxy.sing();

	}

}