package com.learning.rabbitmq.messageconsumer.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

@Service
public class DirectExchangeUnqueuableConsumerService {
	Logger logger = LoggerFactory.getLogger(DirectExchangeUnqueuableConsumerService.class);

	 @RabbitListener(queues = {"unqueableQueue"})
	 //this queue also handles unqueue feature as well as ttl feature
	public void consumeunqueableQueue(String message) {
		logger.info("consumeunqueableQueue: Consuming message {} with algo one", message);
		if (!message.toLowerCase().contains("ram")) {
			// throw new IllegalArgumentException("Message must contains Ram bhagwan's
			// name");
			// the below exception do not put in emanm reque
			// instead based on main queue's dead letter excahnge it pushed to that exachnge
			// with the same routing key as of main exchnage
			throw new AmqpRejectAndDontRequeueException("Message must contains Ram bhagwan's name");
		}
	}

	// below uses manual rejection/acknowledgemenbt
	// so after usage rmeove in application.properties otheriwese all other
	// listeners will be impacted
	@RabbitListener(queues = { "unqueableQueueV2" })
	// using complete rabit mq mesage which contains cusotm proeprties like
	// tag/routng key etc
	public void consumeunqueableQueueV2(Message message, Channel channel) {

		try {
			String readValue = new ObjectMapper().readValue(message.getBody(), String.class);
			logger.info("consumeunqueableQueueV2: Consuming message {} with algo one", readValue);
			if (!readValue.toLowerCase().contains("ram")) {
				// negative case
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
			} else {
				// success case
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = { "dead-letter-queue" })
	public void deadLetterQueue(String message) {
		logger.info("deadLetterQueue: Sending email to admin regarding error message {}", message);
	}
	
	@RabbitListener(queues = { "dead-letter-queue-v2" })
	public void deadLetterQueueV2(String message) {
		logger.info("deadLetterQueueV2: Sending email to admin regarding error message {}", message);
	}
	//This queue comes once TTL fails
	@RabbitListener(queues = { "dead-letter-queue-v3" })
	public void deadLetterQueueV3(String message) {
		logger.info("deadLetterQueueV3: Sending email to admin regarding error message {}", message);
	}
	
	

}
