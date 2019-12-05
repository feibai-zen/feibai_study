package com.feibai.study.demos.demos.collections.datastructure;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.feibai.study.demos.beans.Employee;

public class MyTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.put(20, "bb");
		treeMap.put(1, "aa");
		treeMap.put(10, "dd");

		Set<Entry<Integer, String>> set = treeMap.entrySet();

		for (Iterator<Entry<Integer, String>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<Integer, String> entry = iterator.next();
			System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
		}

		System.out.println("================");

		Map<Employee, String> empTreeMap = new TreeMap<Employee, String>();

		empTreeMap.put(new Employee(20, "xiaoming", 50000), "enginee");
		empTreeMap.put(new Employee(22, "xiaozhang", 50000), "hrbp");
		empTreeMap.put(new Employee(11, "xiaoli", 50000), "pm");

		Set<Entry<Employee, String>> empSet = empTreeMap.entrySet();
		for (Iterator<Entry<Employee, String>> iterator = empSet.iterator(); iterator.hasNext();) {
			Entry<Employee, String> entry = iterator.next();
			System.out
					.println("id:" + entry.getKey().getId() + ",name:" + entry.getKey().getName() + ",value:" + entry);
		}

	}

}
