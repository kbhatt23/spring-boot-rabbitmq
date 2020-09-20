package com.learning.rabbitmq.entity;

public class PaymentInfo {

	private String paymentID;
	private double amount;
	private String cardDetails;
	public PaymentInfo(String paymentID, double amount, String cardDetails) {
		super();
		this.paymentID = paymentID;
		this.amount = amount;
		this.cardDetails = cardDetails;
	}
	public PaymentInfo() {
		super();
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(String cardDetails) {
		this.cardDetails = cardDetails;
	}
	@Override
	public String toString() {
		return "PaymentInfo [paymentID=" + paymentID + ", amount=" + amount + ", cardDetails=" + cardDetails + "]";
	}
	
	
}
