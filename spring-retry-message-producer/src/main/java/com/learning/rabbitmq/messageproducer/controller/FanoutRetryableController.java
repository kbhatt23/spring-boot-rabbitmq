package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rabbitmq.messageproducer.service.FanoutExcahngeService;

@RestController
@RequestMapping("/fanout")
public class FanoutRetryableController {
	@Autowired
	private FanoutExcahngeService svc;

	@GetMapping("/{message}")
	public void sendMessageTofanout(@PathVariable String message) {
		svc.sendFanoutMessage(message);
	}
}
