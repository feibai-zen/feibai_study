package com.feibai.study.demos.demos.foundation.equals_hashcode;

import java.util.HashSet;

/**
 * 重写object类的hashcode()和equals()
 * 
 * @author feibai
 *
 * @Time 2019年8月12日
 *
 */
public class OverrideEqualsAndHashcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Person {
	int age;
	String name;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return name + " - " + age;
	}

	/**
	 * 重写hashCode
	 */
	@Override
	public int hashCode() {
		int nameHash = name.toUpperCase().hashCode();
		return nameHash ^ age;
	}

	/**
	 * 覆盖equals方法
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		// 如果是同一个对象返回true，反之返回false
		if (this == obj) {
			return true;
		}
		// 判断是否类型相同
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Person person = (Person) obj;
		return name.equals(person.name) && age == person.age;
	}

	public static void main(String[] args) {
		// 新建Person对象，
		Person p1 = new Person("eee", 100);
		Person p2 = new Person("eee", 100);
		Person p3 = new Person("aaa", 200);
		Person p4 = new Person("EEE", 100);

		// 新建HashSet对象
		HashSet<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		set.add(p3);

		// 比较p1 和 p2， 并打印它们的hashCode()
		System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
		// 比较p1 和 p4， 并打印它们的hashCode()
		System.out.printf("p1.equals(p4) : %s; p1(%d) p4(%d)\n", p1.equals(p4), p1.hashCode(), p4.hashCode());
		// 打印set
		System.out.printf("set:%s\n", set);
	}
}