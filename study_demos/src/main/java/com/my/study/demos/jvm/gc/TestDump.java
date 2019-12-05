package com.my.study.demos.jvm.gc;

import java.util.Vector;

/**
 * -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/dump.dump -XX:+PrintGCDetails
 * @author leeyuanlong
 *
 * @Time 2019年8月12日
 *
 */
public class TestDump {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("helloworld!");

		Vector vector = new Vector();

		for (int i = 0; i < 20; i++) {
			vector.add(new byte[1 * 1024 * 1024]);
			System.out.println("使用了1M空间.....");
		}

	}

}
