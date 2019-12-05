
package com.feibai.study.demos.demos.multithread.advanced.t07;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通容器
 */
public class Test_01 {

	static List<String> list = new ArrayList<>();
	// static List<String> list = new Vector<>();

	static {
		for (int i = 0; i < 10000; i++) {
			list.add("String " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (list.size() > 0) {
						System.out.println(Thread.currentThread().getName() + " - " + list.remove(0));
					}
				}
			}, "Thread" + i).start();
		}

		/*
		 * for(int i = 0; i < 10; i++){ new Thread(new Runnable() {
		 * 
		 * @Override public void run() { while(true){ synchronized (list) {
		 * if(list.size() <= 0){ break; }
		 * System.out.println(Thread.currentThread().getName() + " - " +
		 * list.remove(0)); } } } }, "Thread" + i).start(); }
		 */
	}

}
