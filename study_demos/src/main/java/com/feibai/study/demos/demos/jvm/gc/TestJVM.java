package com.feibai.study.demos.demos.jvm.gc;

import java.util.HashMap;
import java.util.Map;

/**
 * -Xms1024M -Xmx1024M -XX:+UseSerialGC -XX:MaxTenuringThreshold=15
 * -XX:+PrintGCDetails
 * 
 * @author feibai
 *
 */
public class TestJVM {

	public static void main(String[] args) {

		Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
		for (int i = 0; i < 5; i++) {
			byte[] b = new byte[1024 * 1024];// b申请了空间之后被map引用，不会被回收
			map.put(i, b);
		}

		for (int k = 0; k < 20; k++) {
			for (int j = 0; j < 300; j++) {
				byte[] b = new byte[1024 * 1024];// b申请了内存之后，没有被应用，可以被回收
			}
		}

	}

}
