package com.learning.springbootrabbitmq;

import java.io.Serializable;

public class CustomMessage implements Serializable{
	
	@Override
	public String toString() {
		return "CustomMessage [name=" + name + ", description=" + description + ", footballFan=" + footballFan
				+ ", type=" + type + "]";
	}

	/**
	 * 
	 */
	//below version id should be same in sender and receiver
	private static final long serialVersionUID = -461341195142985955L;
	
	private String name;
	private String description;
	private boolean footballFan;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isFootballFan() {
		return footballFan;
	}
	public void setFootballFan(boolean footballFan) {
		this.footballFan = footballFan;
	}
	public CustomMessage() {
		super();
	}
	public CustomMessage(String name, String description, boolean footballFan,String type) {
		super();
		this.name = name;
		this.description = description;
		this.footballFan = footballFan;
		this.type=type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
