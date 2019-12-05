package com.feibai.study.demos.demos.collections.datastructure;

public class MyHashMap<K, V> {

	Node<K, V>[] table;
	int size;

	public MyHashMap() {
		table = new Node[16];
	}

	public void put(K key, V value) {
		ensureCapacity();
		int key_hash = hash(key.hashCode(), table.length);
		Node<K, V> newNode = new Node<K, V>();
		newNode.hash = key_hash;
		newNode.key = key;
		newNode.value = value;
		newNode.next = null;

		Node tmp = table[key_hash];
		Node lastNode = null;
		if (tmp == null) {
			table[key_hash] = newNode;
			size++;
		} else {// 链表的头指针不为null
			while (tmp != null) {
				if (tmp.key.equals(key)) {// 覆盖旧节点，执行更新操作
					tmp.value = value;
					break;
				} else {
					lastNode = tmp;
					tmp = tmp.next;
				}
			}
			if (null != lastNode) {
				lastNode.next = newNode;
				size++;
			}
		}

	}

	private void ensureCapacity() {
		if (size >= 0.75 * table.length) {
			Node[] new_tableNode = new Node[2 * table.length];
			System.arraycopy(table, 0, new_tableNode, 0, table.length);
			table = new_tableNode;
		}

	}

	private int hash(int v, int length) {
		return v & (length - 1);
	}

	public V get(Object key) {
		int key_hash = hash(key.hashCode(), table.length);

		Node node = table[key_hash];
		Node tmp = node;
		while (tmp != null) {
			if (tmp.key.equals(key)) {
				return (V) tmp.value;
			}
			tmp = tmp.next;
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Node<K, V> node : table) {
			if (null == node)
				continue;
			sb.append("[");
			Node<K, V> tmp = node;
			while (tmp != null) {
				sb.append("hash:" + tmp.hash + ",key:" + tmp.key + ",value:" + tmp.value + ";");
				tmp = tmp.next;
			}
			sb.setCharAt(sb.length() - 1, ']');
			sb.append(",");
		}
		sb.setCharAt(sb.length() - 1, '}');

		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyHashMap<Object, Object> map = new MyHashMap<Object, Object>();
		map.put(1, "aa");
		map.put(2, "bb");
		map.put(3, "cc");
		map.put(4, "ee");
		map.put(5, "ff");
		map.put(5, "gg");
		map.put(11, "hh");
		map.put(12, "jj");
		map.put(13, "kk");
		map.put(14, "ll");
		map.put(1, "mm");
		map.put(11, "hh");
		map.put(12, "jj");
		map.put(13, "kk");
		map.put(14, "ll");
		map.put(1, "mm");

		map.put(1, "ab");
		map.put(2, "bc");
		map.put(3, "cc");
		map.put(4, "ee");
		map.put(5, "ff");
		map.put(535, "gg");
		map.put(4511, "hh");
		map.put(187562, "jj");
		map.put(1233, "kk");
		map.put(1344, "ll");
		map.put(145, "mm");
		map.put(131, "hh");
		map.put(209, "jj");
		map.put(19, "kk");
		map.put(17, "ll");
		map.put(18, "mm");

		System.out.println(map);
		System.out.println(map.get(18));

	}
}
