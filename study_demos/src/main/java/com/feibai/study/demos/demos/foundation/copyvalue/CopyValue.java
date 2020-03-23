package com.feibai.study.demos.demos.foundation.copyvalue;

/**
 * Java中方法参数传递是按值传递
 * 
 * Java中只存在按值传递1）基本类型传递值的副本；2）对象（数组，类，接口）类型的传递，传入函数的值是对象引用的拷贝
 * 
 * @author feibai
 *
 * @Time 2019年8月11日
 *
 */
public class CopyValue {

	public static void main(String[] args) {
		/**
		 * Test 1: Methods can't modify numeric parameters
		 */
		System.out.println("Testing tripleValue:");
		double percent = 10;
		System.out.println("Before: percent=" + percent);
		tripleValue(percent);
		System.out.println("After: percent=" + percent);

		/**
		 * Test 2: Methods can change the state of object parameters
		 */
		System.out.println("\nTesting tripleSalary:");
		Employee harry = new Employee("Harry", 50000);
		System.out.println("Before: salary=" + harry.getSalary());
		tripleSalary(harry);
		System.out.println("After: salary=" + harry.getSalary());

		/**
		 * Test 3: Methods can't attach new objects to object parameters
		 */
		System.out.println("\nTesting swap:");
		Employee a = new Employee("Alice", 70000);
		Employee b = new Employee("Bob", 60000);
		System.out.println("Before: a=" + a.getName());
		System.out.println("Before: b=" + b.getName());
		swap(a, b);
		System.out.println("After: a=" + a.getName());
		System.out.println("After: b=" + b.getName());
	}

	private static void swap(Employee x, Employee y) {
		Employee temp = x;
		x = y;
		y = temp;
		System.out.println("End of method: x=" + x.getName());
		System.out.println("End of method: y=" + y.getName());
	}

	private static void tripleSalary(Employee x) {
		x.setSalary(x.getSalary() * 3);
		System.out.println("End of method: salary=" + x.getSalary());
	}

	private static void tripleValue(double x) {
		x = 3 * x;
		System.out.println("End of Method X= " + x);
	}

}

class Employee {

	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
