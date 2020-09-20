package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//added for spring oob retrying mechanism
//@Service
public class FanoutExchangeConsumerService {
	Logger logger = LoggerFactory.getLogger(FanoutExchangeConsumerService.class);
	@RabbitListener(queues = {"fanout-one"})
	public void consumeAlgoOne(String message) {
		logger.info("consumeAlgoOne: Consuming message {} with algo one",message);
		//if(!message.toLowerCase().contains("ram")) {
			//if this is thrown it goes back to reques for max attemp of retry and eac time it wait for max interval before coming back to listnere
		//	throw new IllegalArgumentException("message must contain ram bhagwan's name");
		//}else {
			//success
			logger.info("consumeAlgoOne: succesfully processed message {} with algo one",message);
		//}
	}
	
	@RabbitListener(queues = {"fanout-two"}, concurrency = "4")
	public void consumeAlgoTwo(String message) {
		logger.info("consumeAlgoTwo: Consuming message {} with algo two",message);
		//for algo two we are assuming since there is no ram , there is no way out of even retrying as it will alway fail
		if(!message.toLowerCase().contains("ram")) {
			//throw new IllegalArgumentException("message must contain ram bhagwan's name");
			throw new AmqpRejectAndDontRequeueException("message must contain ram bhagwan's name");
		}else {
			//success
			logger.info("consumeAlgoTwo: succesfully processed message {} with algo two",message);
		}
	}
	@RabbitListener(queues = {"dead-letter-queue-one"})
	public void consumeDeadLetterAlgoOne(String message) {
		logger.info("consumeDeadLetterAlgoOne: Sending email to admin for message",message);
	}
	@RabbitListener(queues = {"dead-letter-queue-two"})
	public void consumeDeadLetterAlgoTwo(String message) {
		logger.info("consumeDeadLetterAlgoTwo: Sending email to admin for message",message);
	}
}
