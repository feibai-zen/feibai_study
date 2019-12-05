package com.my.study.designpatterns.proxy.proxydynamic2;


public class Client {

	public static void main(String[] args){
		LogHandler logHandler=new LogHandler();

		// new UserManagerImpl()是被代理的对象
		UserManager userManager=(UserManager)logHandler.newProxyInstance(new UserManagerImpl());
		// UserManager userManager=new UserManagerImpl();
		userManager.addUser("1111", "张三");
	}
}
