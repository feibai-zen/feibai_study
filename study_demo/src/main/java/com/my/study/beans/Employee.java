package com.my.study.beans;

public class Employee implements Comparable<Employee> {
	private Integer id;

	private String name;
	private double salary;

	public Employee(int id, String name, double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		if (o == this) {
			return 0;
		}
		if (this.id > o.id) {
			return 1;
		}
		if (o.id < this.id) {
			return -1;
		}
		return -1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
