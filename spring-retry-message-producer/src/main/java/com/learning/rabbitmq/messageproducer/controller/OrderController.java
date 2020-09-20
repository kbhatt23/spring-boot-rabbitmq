package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.entity.OrderCreationEntity;
import com.learning.rabbitmq.entity.OrderDefaultEntity;
import com.learning.rabbitmq.entity.OrderSubmissionEntity;
import com.learning.rabbitmq.messageproducer.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService service;
	
	@PostMapping("/v1")
	public void createOrder(@RequestBody OrderCreationEntity order) {
		 service.createOrderRabbitV1(order);
	}
	
	@PutMapping("/v1")
	public void submitOrder(@RequestBody OrderSubmissionEntity order) {
		 service.submitOrderRabbitV1(order);
	}
	
	@PostMapping("/v2")
	public void createOrderV2(@RequestBody OrderCreationEntity order) {
		 service.createOrderRabbitV2(order);
	}
	
	@PutMapping("/v2")
	public void submitOrderV2(@RequestBody OrderSubmissionEntity order) {
		 service.submitOrderRabbitV2(order);
	}
	//sending an object that is not present in consumer
	//default rabbithadler flow
	@PostMapping("/v3")
	public void createOrderV3(@RequestBody OrderDefaultEntity order) {
		 service.createOrderRabbitV3(order);
	}
	
	//coinsistne has code based
	//same routing key object will always go to same queue always
	//also the binding key added in exchange is integer that just give
	// if one is 10 and other is 20 then number of messages in queue 2 will be twice as that of one
	@PostMapping("/v4")
	public void createOrderV4(@RequestBody OrderCreationEntity order) {
		 service.createOrderRabbitV4(order);
	}
	
	@PutMapping("/v4")
	public void submitOrderV4(@RequestBody OrderSubmissionEntity order) {
		 service.submitOrderRabbitV4(order);
	}
}
