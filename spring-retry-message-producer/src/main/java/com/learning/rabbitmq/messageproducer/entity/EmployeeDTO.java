package com.learning.rabbitmq.messageproducer.entity;

public class EmployeeDTO {
	private String name;
	private int age;
	private double salary;
	private String id;

	// creating this for unmarshalling
	public EmployeeDTO() {
	}

	public EmployeeDTO(String name, int age, double salary, String id) {
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
