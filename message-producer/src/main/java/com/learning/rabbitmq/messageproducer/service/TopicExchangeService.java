package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TopicExchangeService extends RabbitUsageService{
	private Logger logger = LoggerFactory.getLogger(TopicExchangeService.class);
	private static final String TOPIC_EXCHANGE_NAME = "custom-topic-exchange";

	public void sendTopicExchange(String routingKey, String message) {
		logger.info("sendDirectExchange: Sending message: {} with routing key {}",message,routingKey);
		//coud use jackson based json for objects
		getRabbitTemplate().convertAndSend(TOPIC_EXCHANGE_NAME, routingKey, message);
	}
}
