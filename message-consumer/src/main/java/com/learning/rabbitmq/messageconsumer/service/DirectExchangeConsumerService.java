package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class DirectExchangeConsumerService {
	Logger logger = LoggerFactory.getLogger(DirectExchangeConsumerService.class);
	@RabbitListener(queues = {"algo-one"})
	public void consumeAlgoOne(String message) {
		logger.info("consumeAlgoOne: Consuming message {} with algo one",message);
	}
	
	@RabbitListener(queues = {"algo-two"})
	public void consumeAlgoTwo(String message) {
		logger.info("consumeAlgoTwo: Consuming message {} with algo two",message);
	}
	@RabbitListener(queues = {"algo-three"})
	public void consumeAlgoThree(String message) {
		logger.info("consumeAlgoThree: Consuming message {} with algo three",message);
	}
}
