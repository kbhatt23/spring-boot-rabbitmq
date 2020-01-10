package com.learning.springbootrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootrabbitmq.config.RabbitMQMessageConstants;

@RestController
public class RabbitMQMessageController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@GetMapping("/produceMessage1/{message}")
	public String sendMessage1(@PathVariable String message) {
		CustomMessage message1 =  new CustomMessage(message, "jai shree ram, jai hanuman", true, "codeType1");
		
		rabbitTemplate.convertAndSend(RabbitMQMessageConstants.EXCHANGE_TYPE_1, RabbitMQMessageConstants.ROUTING_KEY_TYPE1, message1);
		return "jai shree ram from message1";
	}
	
	/*
	 * @GetMapping("/produceGenericMessage/{message}") public String
	 * sendGenericMessage(@PathVariable String message) { CustomMessage message1 =
	 * new CustomMessage(message, "jai shree ram, jai hanuman", true, "codeType1");
	 * 
	 * rabbitTemplate.convertAndSend(RabbitMQMessageConstants.EXCHANGE_TYPE_GENERIC,
	 * RabbitMQMessageConstants.ROUTING_KEY_GENERIC, message1); return
	 * "jai shree ram from generic message"; }
	 */
}
