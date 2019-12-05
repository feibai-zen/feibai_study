package com.feibai.study.demos.demos.jvm.gc;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * 提供了打印垃圾收集器名称的功能
 * 
 * @author leeyuanlong
 *
 */
public class GarbageTest {

	public static void main(String[] args) {
		List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean b : l) {
			System.out.println(b.getCollectionCount());
			System.out.println(b.getMemoryPoolNames());
			System.out.println(b.getName());
		}
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
