package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.messageproducer.service.MessageProducerService;

@RestController
public class MessageController {
	@Autowired
	private MessageProducerService messageProducerService;

	@GetMapping("/hello-world/{name}")
	public void helloWorld(@PathVariable String name) {
		// lets not create excahnge and directly use queu name
		// will use default exchange auto create in rabbit mq whihc passes to queue name
		// directly
		// also we cna use oob created excahnges in rabbit mq
		messageProducerService.produceHelloWorldMessage("hello " + name);

	}
	
	@GetMapping("/hello-world-fanout/{name}")
	public void helloWorldFanout(@PathVariable String name) {
		// lets not create excahnge and directly use queu name
		// will use default exchange auto create in rabbit mq whihc passes to queue name
		// directly
		// also we cna use oob created excahnges in rabbit mq
		messageProducerService.produceHelloWorldCopyMessage("hello " + name);

	}
}
