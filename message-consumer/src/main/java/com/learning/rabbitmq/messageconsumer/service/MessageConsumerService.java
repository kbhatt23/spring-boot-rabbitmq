package com.learning.rabbitmq.messageconsumer.service;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class MessageConsumerService {
	private Logger logger = LoggerFactory.getLogger(MessageConsumerService.class);

	@RabbitListener(queues = {"basic.hello-world"})
	public void fetchBasicHelloMessage(String message) {
		//System.out.println("Message recieved "+message);
		logger.info("fetchBasicHelloMessage:Message recieved "+message);
	}
	
	//this copy is created to demonstrate fanout exchange and other type of excahnges also
	@RabbitListener(queues = {"basic.hello-world-copy"})
	public void fetchBasicHelloCopyMessage(String message) {
		//System.out.println("Message recieved "+message);
		logger.info("fetchBasicHelloCopyMessage:Message recieved "+message);
	}
	
	
	
	//since rate of production is twice than consumtopm rate
	//we could either have 2 instance of consumer running or have only one instance and concurrnecy as 2
	@RabbitListener(queues = {"basic.fixed-rate"} , concurrency = "2")
	public void fetchFixedRateQueue1(String message) {
		logger.info("fetchFixedRateQueue:Message recieved "+message +" by thread "+Thread.currentThread().getName());
		//assuming its a big task to consume
		//rate of pruction is 1 sec and consumtion is 2 sec
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//why to waste and create copy of smae method
	//better to use concurrnecy level using async thread pool in spring
	//@RabbitListener(queues = {"basic.fixed-rate"})
	/*
	 * public void fetchFixedRateQueue2(String message) {
	 * logger.info("fetchFixedRateQueue2:Message recieved "+message); //assuming its
	 * a big task to consume //rate of pruction is 1 sec and consumtion is 2 sec try
	 * { Thread.sleep(2000); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
}
