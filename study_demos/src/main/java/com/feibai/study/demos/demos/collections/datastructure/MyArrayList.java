package com.feibai.study.demos.demos.collections.datastructure;

public class MyArrayList<E> {
//自定义一个ArrayList

	public static void main(String[] args) {
		MyArrayList<Integer> List = new MyArrayList<Integer>();
		List.add(1);
		List.add(2);
		System.out.println(List);
		System.out.println(List.size());
		System.out.println(List.get(20));
	}

	private static final int DEFAULT_SIZE = 20;

	private transient Object[] elementData;
	private int size;

	public MyArrayList() {
		elementData = new Object[DEFAULT_SIZE];
		this.size = 0;
	}

	public MyArrayList(int capacity) {
		elementData = new Object[capacity];
		size = 0;
	}

	public void add(E element) {
		ensureCapacity();
		elementData[size++] = element;
	}

	public E get(int index) {
		if (isOutOfBoundary(index))
			throw new ArrayIndexOutOfBoundsException("Out of ArrayList size.");

		return (E) elementData[index];
	}

	public void set(Integer index, E element) {

		if (isOutOfBoundary(index))
			throw new ArrayIndexOutOfBoundsException("Out of ArrayList size.");
		elementData[index] = element;

	}

	private boolean isOutOfBoundary(int index) {
		if (index < 0 || index >= size) {
			return true;
		}
		return false;

	}

	private void ensureCapacity() {
		// TODO:判断数组是否需要扩容
		if (size >= elementData.length) {
			Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
	}

	@Override
	public String toString() {

		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("[");
		for (int i = 0; i < size; i++) {
			sbBuilder.append(elementData[i] + ",");
		}
		sbBuilder.setCharAt(sbBuilder.length() - 1, ']');
		return sbBuilder.toString();
	}

	public int size() {
		return this.size;
	}

	public void remove(int index) {
		if (isOutOfBoundary(index)) {
			throw new ArrayIndexOutOfBoundsException("Out of ArrayList size.");
		}

		if (index == size - 1) {
			elementData[size - 1] = null;
			size--;
		}

		if (index < size - 1) {
			System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
			elementData[size] = null;
			size--;
		}

	}

	public void remove(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					remove(i);
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elementData[i].equals(element)) {
					remove(i);
				}
			}
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
