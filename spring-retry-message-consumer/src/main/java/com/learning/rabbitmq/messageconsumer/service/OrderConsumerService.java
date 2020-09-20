package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.entity.OrderCreationEntity;
import com.learning.rabbitmq.entity.OrderSubmissionEntity;

//@Service
public class OrderConsumerService {
	Logger logger = LoggerFactory.getLogger(OrderConsumerService.class);
	
	@RabbitListener(queues = {"order.creation.queue"})
	public void createOrder(OrderCreationEntity order) {
		logger.info("createOrder: Creating order "+order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RabbitListener(queues = {"order.submission.queue"})
	public void submitOrder(OrderSubmissionEntity order) {
		logger.info("submitOrder: submitting order "+order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//same queue but object entity si differnet and haenc esame queue an hanldle differne message objects
	//ultimately it is byte[]
	@RabbitListener(queues = {"order.combined.queue"})
	public void createOrderV2(OrderCreationEntity order) {
		logger.info("createOrderV2: Creating order "+order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RabbitListener(queues = {"order.combined.queue"})
	public void submitOrderV2(OrderSubmissionEntity order) {
		logger.info("submitOrderV2: submitting order "+order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
