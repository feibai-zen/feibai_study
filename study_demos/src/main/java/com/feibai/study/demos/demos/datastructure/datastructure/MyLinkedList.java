package com.feibai.study.demos.demos.datastructure.datastructure;

public class MyLinkedList<E> {

	Node<E> firstNode;
	Node<E> lastNode;
	int size;

	// 插入元素使用头插、尾插
	public void addLast(E element) {
		Node<E> node = new Node<E>(firstNode, firstNode.next, element);
		size++;
	}

	public void addBefore(E element) {

	}

	class Node<E> {
		Node<E> previous;
		Node<E> next;
		E element;

		public Node(Node<E> previous, Node<E> next, E element) {
			this.previous = previous;
			this.next = next;

		}
	}

}
