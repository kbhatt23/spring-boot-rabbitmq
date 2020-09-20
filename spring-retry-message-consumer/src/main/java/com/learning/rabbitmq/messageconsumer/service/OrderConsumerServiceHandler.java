package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.learning.rabbitmq.entity.OrderCreationEntity;
import com.learning.rabbitmq.entity.OrderSubmissionEntity;

//@Service
@RabbitListener(queues = { "order.combined.queue" })
public class OrderConsumerServiceHandler {
	Logger logger = LoggerFactory.getLogger(OrderConsumerServiceHandler.class);

	// same queue but object entity si differnet and haenc esame queue an hanldle
	// differne message objects
	// ultimately it is byte[]
	// below annotation is needed for all the listnering methods as other method cna
	// also be there whihc have no linking with queue listnenig
	@RabbitHandler
	public void createOrderV2(OrderCreationEntity order) {
		logger.info("createOrderV2: Creating order " + order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitHandler
	public void submitOrderV2(OrderSubmissionEntity order) {
		logger.info("submitOrderV2: submitting order " + order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//in case class is not present we can take here
	@RabbitHandler(isDefault = true)
	public void fetchDefaultObjectMessage(Object order) {
		logger.info("fetchDefaultObjectMessage: fetching order " + order);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
