package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.messageproducer.entity.MessageConstants;
import com.learning.rabbitmq.messageproducer.service.CodeBuildMessgaeService;

@RestController
@RequestMapping("/code-messages")
public class CodeBuiltMessageController {
	@Autowired
	private CodeBuildMessgaeService codeBuildMessgaeService;
	@GetMapping("/direct/{routingKey}/{message}")
	public void sendDirectMessageUsingCodeBuiltEntitties(@PathVariable String routingKey,@PathVariable String message) {
		if(routingKey.equals(MessageConstants.CODE_BUILT_DIRECT_ROUTE1) || routingKey.equals(MessageConstants.CODE_BUILT_DIRECT_ROUTE2)) {
			codeBuildMessgaeService.sendDirectMessage(message, routingKey);
		}else{
			throw new RuntimeException("Please enter valid routing key "+MessageConstants.CODE_BUILT_DIRECT_ROUTE1+" or "+MessageConstants.CODE_BUILT_DIRECT_ROUTE2)  ;
		}
	}
	
	@GetMapping("/fanout/{message}")
	public void sendFanoutMessageUsingCodeBuiltEntitties(@PathVariable String message) {
			codeBuildMessgaeService.sendFanoutMessage(message);
	}
}
