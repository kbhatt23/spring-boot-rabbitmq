package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.rabbitmq.messageproducer.entity.EmployeeDTO;
import com.learning.rabbitmq.messageproducer.service.DirectRetryableExchangeService;

@RestController
@RequestMapping("/direct-retryable")
public class DirectRetryableController {
	@Autowired
	private DirectRetryableExchangeService service;
	@PostMapping("/{routingKey}")
	public void sendDirectMessage(@PathVariable String routingKey, @RequestBody EmployeeDTO employeeDTO) {
		try {
			service.sendDirectExchange(routingKey, employeeDTO);
		} catch (AmqpException | JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
