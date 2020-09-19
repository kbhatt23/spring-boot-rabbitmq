package com.learning.rabbitmq.messageproducer.entity;

import java.io.Serializable;

public class Employee  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -675169256060953452L;
	private String name;
	private int failureCount;
	public Employee(String name, int failureCount) {
		super();
		this.name = name;
		this.failureCount = failureCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", failureCount=" + failureCount + "]";
	}
	public Employee() {
		super();
	}
}
