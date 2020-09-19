package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService extends RabbitUsageService {
	// we know that this is singleton class per bean
	private int i = 1;

	private Logger logger = LoggerFactory.getLogger(MessageProducerService.class);
	// moved it to super class
	// @Autowired
	// private RabbitTemplate rabbitTemplate;

	// using default exchange oob created by rabbit
	// passes directly to quue
	// also byte array conversion will be taken care by rabbittempalte automaticall
	// also if needed rabbit tempalte can create exchanges,queues , bindings etc
	public void produceHelloWorldMessage(String message) {
		// routing key in default excahnge will be passed to queue with same name -> no
		// need to create exchange at all
		String routingKey = "basic.hello-world";
		// System.out.println("Sending hello world message "+ message+"to queue:
		// "+routingKey);
		logger.info("produceHelloWorldMessage: Sending hello world message " + message + "to queue: " + routingKey);
		getRabbitTemplate().convertAndSend(routingKey, message);
	}
	//serving as type of fanout exchange
	public void produceHelloWorldCopyMessage(String message) {
		// routing key in default excahnge will be passed to queue with same name -> no
		// need to create exchange at all
		String routingKey = "jai shree ram routing key";
		// System.out.println("Sending hello world message "+ message+"to queue:
		// "+routingKey);
		logger.info("produceHelloWorldCopyMessage: Sending hello world message " + message + "to queue: " + routingKey);
		//getRabbitTemplate().convertAndSend(routingKey, message);
		//since fanout we cna keep routing key just abt anything -> as it passes to all the queue bound to the exchange
		getRabbitTemplate().convertAndSend("custom-fanout-exchange", routingKey, message);
	}

	// a scheduled task to update some property in consumer table
	// calls after every one second
	// uses executor service behind the scene
	// @Scheduled(fixedRate = 1000)
	public void sendFixedRateMessage() {
		String routingKey = "basic.fixed-rate";
		String message = "jai shree ram " + i;
		logger.info("Sending fixed rate message " + message + "to queue: " + routingKey);
		getRabbitTemplate().convertAndSend(routingKey, message);
		i++;
	}
}
