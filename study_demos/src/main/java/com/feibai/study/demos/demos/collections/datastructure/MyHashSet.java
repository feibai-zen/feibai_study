package com.feibai.study.demos.demos.collections.datastructure;

import java.util.HashMap;

public class MyHashSet<E> {

	HashMap<E, Object> map;

	private static final Object PRESENT = new Object();

	public MyHashSet() {
		map = new HashMap<E, Object>();
	}

	public void add(E o) {
		map.put(o, PRESENT);
	}

}
