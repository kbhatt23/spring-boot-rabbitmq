package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.messageproducer.entity.Employee;
import com.learning.rabbitmq.messageproducer.entity.EmployeeDTO;

@Service
public class DirectRetryableExchangeService extends RabbitUsageService{
	private Logger logger = LoggerFactory.getLogger(DirectRetryableExchangeService.class);
	private static final String DIRECT_EXCHANGE_NAME = "employee-direct-exchange";
	private ObjectMapper objectMapper = new ObjectMapper();

	public void sendDirectExchange(String routingKey, EmployeeDTO employeeDTO) throws AmqpException, JsonProcessingException {
		Employee employee = objectMapper.convertValue(employeeDTO, Employee.class);
		logger.info("sendDirectExchange: Sending message: {} with routing key {}",employee,routingKey);
		//coud use jackson based json for objects
		
		getRabbitTemplate().convertAndSend(DIRECT_EXCHANGE_NAME, routingKey, employee);
	}
}
