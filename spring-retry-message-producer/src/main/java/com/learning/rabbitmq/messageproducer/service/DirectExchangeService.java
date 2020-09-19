package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DirectExchangeService extends RabbitUsageService{
	private Logger logger = LoggerFactory.getLogger(DirectExchangeService.class);
	private static final String DIRECT_EXCHANGE_NAME = "custom-direct-exchange";

	public void sendDirectExchange(String routingKey, String message) {
		logger.info("sendDirectExchange: Sending message: {} with routing key {}",message,routingKey);
		//coud use jackson based json for objects
		getRabbitTemplate().convertAndSend(DIRECT_EXCHANGE_NAME, routingKey, message);
	}
	//unqueableRoute as routing key
	public void sendDirectExchangeUnQueable(String routingKey, String message) {
		logger.info("sendDirectExchangeUnQueable: Sending message: {} with routing key {}",message,routingKey);
		//coud use jackson based json for objects
		getRabbitTemplate().convertAndSend(DIRECT_EXCHANGE_NAME, routingKey, message);
	}
}
