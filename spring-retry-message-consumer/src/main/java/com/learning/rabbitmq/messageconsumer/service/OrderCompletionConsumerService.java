package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.entity.OrderCompletionEntity;

@Service
public class OrderCompletionConsumerService {

	Logger logger = LoggerFactory.getLogger(OrderCompletionConsumerService.class);
	
	@RabbitListener(queues = "order.completion")
	public void consumeCompeltion(OrderCompletionEntity order) {
		logger.info("consumeCompeltion: final order message "+order);
	}
}
