package com.learning.rabbitmq.messageconsumer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//real rabbit mq api have so may proeprties
//we just need length and anme thats it
@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitMQQueue {
	@JsonProperty(required = true, value = "name")
	private  String queueName;
	
	@JsonProperty(required = true, value = "messages")
	private  String queueLength;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(String queueLength) {
		this.queueLength = queueLength;
	}
	
}
