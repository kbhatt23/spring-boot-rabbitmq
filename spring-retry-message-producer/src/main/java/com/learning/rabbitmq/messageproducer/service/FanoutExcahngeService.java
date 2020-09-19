package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class FanoutExcahngeService extends RabbitUsageService{
	private static final String FANOUT_EXCHANNGE_EXERCISE = "custom-fanout-exchange";
	private Logger logger = LoggerFactory.getLogger(FanoutExcahngeService.class);

//no need of routing key as it goes to all bound queues to this speicif excange
	public void sendFanoutMessage(String message) {
		logger.info("sendFanoutMessage: with message " + message);
		getRabbitTemplate().convertAndSend(FANOUT_EXCHANNGE_EXERCISE,"", message);
	}
}
