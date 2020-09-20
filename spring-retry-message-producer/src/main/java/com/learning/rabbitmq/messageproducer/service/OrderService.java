package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.entity.OrderCreationEntity;
import com.learning.rabbitmq.entity.OrderDefaultEntity;
import com.learning.rabbitmq.entity.OrderSubmissionEntity;
@Service
public class OrderService extends RabbitUsageService{
	private static final String ORDER_EXCAHNGE = "order.exchange";
	private static final String ORDER_CONSITENT_HASH_EXCAHNGE = "custom-hash-exchange";
	
	private static final String ORDER_CREATION_ROUTING_KEY = "order.creation.routing";
	private static final String ORDER_SUBMISSION_ROUTING_KEY = "order.submission.routing";
	//lets push the differnet messages objects in the same queue
	//since rabbit mq handle byte array anyobject is byte array , spring boot allow multle rabbitlister with diffenret method argument to support this
	//push to same quuee , but consumer will be differnet 
	private static final String ORDER_COMBINED_ROUTING_KEY = "order.combined.routing";
	private Logger logger = LoggerFactory.getLogger(EmployeeProducerService.class);
	public void createOrderRabbitV1(OrderCreationEntity order) {
		logger.info("createOrderRabbitV1: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_CREATION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_EXCAHNGE, ORDER_CREATION_ROUTING_KEY, order);
	}
	
	public void submitOrderRabbitV1(OrderSubmissionEntity order) {
		logger.info("submitOrderRabbitV1: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_SUBMISSION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_EXCAHNGE, ORDER_SUBMISSION_ROUTING_KEY, order);
	}
	
	public void createOrderRabbitV2(OrderCreationEntity order) {
		logger.info("createOrderRabbitV2: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_CREATION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_EXCAHNGE, ORDER_COMBINED_ROUTING_KEY, order);
	}
	public void submitOrderRabbitV2(OrderSubmissionEntity order) {
		logger.info("submitOrderRabbitV2: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_SUBMISSION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_EXCAHNGE, ORDER_COMBINED_ROUTING_KEY, order);
	}
	
	public void createOrderRabbitV3(OrderDefaultEntity order) {
		logger.info("createOrderRabbitV3: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_CREATION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_EXCAHNGE, ORDER_COMBINED_ROUTING_KEY, order);
	}
	
	//consisten hashing excahnge -> maintains order of the message , 
	//meaning same order id differnet messages/events like order creatio/submision.placment will go to same queue 
	public void createOrderRabbitV4(OrderCreationEntity order) {
		logger.info("createOrderRabbitV4: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_CREATION_ROUTING_KEY);
		//order.getID -> for same order different message event will have same routing key and hence hash code will be same and hence will go to same old queue
		getRabbitTemplate().convertAndSend(ORDER_CONSITENT_HASH_EXCAHNGE, order.getId(), order);
	}
	
	public void submitOrderRabbitV4(OrderSubmissionEntity order) {
		logger.info("submitOrderRabbitV4: pushing order {} to exchange {} with routing key {}",order,ORDER_EXCAHNGE,ORDER_SUBMISSION_ROUTING_KEY);
		getRabbitTemplate().convertAndSend(ORDER_CONSITENT_HASH_EXCAHNGE, order.getId(), order);
	}
}
