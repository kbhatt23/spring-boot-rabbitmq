package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.messageproducer.entity.MessageConstants;

//This class expects , excahnges , binding and queues to be created using code
//instead of creating in rabbit mq managmenet admin ui
@Service
public class CodeBuildMessgaeService extends RabbitUsageService{

	private Logger logger = LoggerFactory.getLogger(CodeBuildMessgaeService.class);
	
	public void sendDirectMessage(String message, String routingKey) {
		logger.info("sending message {} with routingKey {}",message,routingKey);
		getRabbitTemplate().convertAndSend(MessageConstants.CODE_BUILT_DIRECT_EXCHANGE, routingKey, message);
	}
	
	public void sendFanoutMessage(String message) {
		logger.info("sending message {} with routingKey {}",message,"empty");
		getRabbitTemplate().convertAndSend(MessageConstants.CODE_BUILT_FANOUT_EXCHANGE, "",message);
	}
}
