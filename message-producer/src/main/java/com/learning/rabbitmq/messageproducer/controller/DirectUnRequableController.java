package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.messageproducer.service.DirectExchangeService;

@RestController
@RequestMapping("/directUnQueable")
public class DirectUnRequableController {
	@Autowired
	private DirectExchangeService service;
	@GetMapping("/{routingKey}/{message}")
	public void sendDirectMessage(@PathVariable String routingKey, @PathVariable String message ) {
		service.sendDirectExchangeUnQueable(routingKey, message);
	}
	
	
}
