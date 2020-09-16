package com.learning.rabbitmq.messageproducer.entity;

import java.io.Serializable;

public class EmployeeSerializable /* implements Serializable */ {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8728033564650266704L;

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + ", id=" + id + "]";
	}

	private String name;
	private int age;
	private double salary;
	private String id;

	// creating this for unmarshalling
	public EmployeeSerializable() {
	}

	public EmployeeSerializable(String name, int age, double salary, String id) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
