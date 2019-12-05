package com.feibai.study.demos.beans;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String id;
	private String name;
	List<User> students = new ArrayList<User>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}
}