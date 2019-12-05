package com.feibai.study.demos.demos.collections.iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestIterator {

	public static void main(String[] args) {

		/**
		 * List迭代器访问方式
		 */
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("List:this is " + i);
		}
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String element = iterator.next();
			System.out.println(element);
		}

		/**
		 * Set的迭代器访问方式
		 */
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < 20; i++) {
			set.add("set: this is " + i);
		}
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}

		/**
		 * Map的迭代器访问方式
		 */
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < 20; i++) {
			map.put(i, "Map:this is " + i);
		}

		Set<Map.Entry<Integer, String>> mapset = map.entrySet();
		for (Iterator<Map.Entry<Integer, String>> iterator = mapset.iterator(); iterator.hasNext();) {
			Map.Entry<Integer, String> tmp = iterator.next();
			System.out.println("key:" + tmp.getKey() + ",value:" + tmp.getValue());
		}
	}
}
