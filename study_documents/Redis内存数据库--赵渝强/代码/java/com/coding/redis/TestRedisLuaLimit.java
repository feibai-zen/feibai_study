package com.coding.redis;

public class TestRedisLuaLimit {

	public static void main(String[] args) {
		// 启动多个线程，模拟高并发的场景
		for(int i=0;i<10;i++) {
			new Thread(new AccessApplication(i)).start();
		}
	}
}
