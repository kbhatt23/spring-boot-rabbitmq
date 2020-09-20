package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.entity.OrderCompletionEntity;
import com.learning.rabbitmq.entity.OrderCreationEntity;
import com.learning.rabbitmq.entity.OrderSubmissionEntity;

@Service
@RabbitListener(queues = "hash-queue-two")
public class OrderHashQueueTwoConsumer {
	Logger logger = LoggerFactory.getLogger(PaymentInfoConsumerService.class);
	@RabbitHandler
	public void recieveORderCreateEvent(OrderCreationEntity order) {
		logger.info("recieveORderCreateEvent: creating order event for "+order);
	}
	
	//we are putting data in another queuue
	//this helps in implementing saga patter(choreography based)
	//could have used rabbit tempalte but that is too much
	@RabbitHandler
	//exchange name is order.completion and since it is fanout giving empty routing key
	@SendTo("order.completion/")
	public /*void*/ OrderCompletionEntity recieveORderSubmitEvent(OrderSubmissionEntity order) {
		logger.info("recieveORderCreateEvent: submitting order event for "+order);
		ObjectMapper mapper = new ObjectMapper();
		OrderCompletionEntity returnResult =mapper.convertValue(order, OrderCompletionEntity.class);
		return returnResult;
	}
}
