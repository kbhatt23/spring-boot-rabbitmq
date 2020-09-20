package com.learning.rabbitmq.entity;

import java.util.List;

public class OrderCompletionEntity {


	private String id;
	private List<String> items;
	private double totalPrice;
	private String address;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<String> getItems() {
		return items;
	}


	public void setItems(List<String> items) {
		this.items = items;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public OrderCompletionEntity() {
		super();
	}


	public OrderCompletionEntity(String id, List<String> items, double totalPrice, String address) {
		super();
		this.id = id;
		this.items = items;
		this.totalPrice = totalPrice;
		this.address = address;
	}


	@Override
	public String toString() {
		return "OrderCompletionEntity [id=" + id + ", items=" + items + ", totalPrice=" + totalPrice + ", address="
				+ address + "]";
	}


}
