package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.messageproducer.service.TopicExchangeService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private TopicExchangeService service;
	@GetMapping("/{routingKey}/{message}")
	public void sendDirectMessage(@PathVariable String routingKey, @PathVariable String message ) {
		service.sendTopicExchange(routingKey, message);
	}
}
