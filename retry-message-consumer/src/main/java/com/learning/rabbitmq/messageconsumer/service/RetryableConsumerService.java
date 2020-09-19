package com.learning.rabbitmq.messageconsumer.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.messageproducer.entity.Employee;
import com.rabbitmq.client.Channel;

//@Service
public class RetryableConsumerService {
	private Logger logger = LoggerFactory.getLogger(RetryableConsumerService.class);
	
	private static final String DEAD_LETTER_EXCHANGE = "employee-dead-letter-exchange";
	private ObjectMapper objectMapper  = new ObjectMapper();
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private static int count=0;

	@RabbitListener(queues = { "retryable-employee-queue" })
	public void readRetryableEnmployee(Message message, Channel channel) throws JsonParseException, JsonMappingException, IOException {
	//	String employeeStr= objectMapper.readValue(message.getBody(), String.class);
		Employee employee = objectMapper.readValue(message.getBody(), Employee.class);
		logger.info("readRetryableEnmployee: recieving employee "+employee);
		if(!employee.getName().toLowerCase().contains("ram")) {
			//error case
			//retry case
			if(count == 3) {
				count=0;
				rabbitTemplate.convertAndSend(DEAD_LETTER_EXCHANGE, message.getMessageProperties().getReceivedRoutingKey(), employee);
			}else {
				employee.setFailureCount(employee.getFailureCount()+1);
				count++;
				throw new AmqpRejectAndDontRequeueException("Employee name must contain ram bhagwan's name");
			}
		}else {
			//succes case
			logger.info("readRetryableEnmployee: employee {} processed sucesfully ",employee);
		}
	}
	
	@RabbitListener(queues = {"dead-letter-queue"})
	public void listenDeadQueue(Employee employee) {
		logger.info("listenDeadQueue: sending email to admin regarding message: "+employee);
	}
}
