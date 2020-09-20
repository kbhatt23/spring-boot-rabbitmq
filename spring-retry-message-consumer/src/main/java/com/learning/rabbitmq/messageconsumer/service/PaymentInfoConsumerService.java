package com.learning.rabbitmq.messageconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.entity.PaymentInfo;

//@Service
public class PaymentInfoConsumerService {
	Logger logger = LoggerFactory.getLogger(PaymentInfoConsumerService.class);
	
	@Autowired
	private RabbitListenerEndpointRegistry registry;

	// consuming queues
	//prefecth default message is 250
	//so if 500 messgaes are there in queu then it will pic and tak 250 in local queue
	//that way other consumer cna take 250 only, 
	//if proces sis slow even if we add more consumers it wont help as they will be idel
	//once messages are prefetched it cna not be assinged ot another listner
	@RabbitListener(queues = { "fanout.queue-one" } , concurrency = "2" , containerFactory = "prefetchContianerFactory")
	public void consumePaymentOne(PaymentInfo pi) {
		logger.info("consumePaymentOne: processing payment " + pi);
		try {
			//extremly slow
			//the prefetched items willbe 250*2 , menaing they will not be ablaible for new consumers
			//thats why in this case we shoudl keep prefetch value to 1
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//default prefetch goes to 250
	@RabbitListener(queues = { "fanout.queue-two" }, concurrency = "2")
	public void consumePaymentTwo(PaymentInfo pi) {
		logger.info("consumePaymentTwo: processing payment " + pi);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//will start at 5 : 16 pm every day every week
	@Scheduled(cron = "0 16 17 * * ?")
	public void stopAllQueues() {
		logger.info("stopAllQueues: Stopping all the queues");
		registry.getListenerContainers()
		.forEach(container -> {
			logger.info("stopAllQueues: stopping container " + container);
			container.stop();
		});
	}
	
	@Scheduled(cron = "0 17 17 * * ?")
	public void startAllQueues() {
		logger.info("startAllQueues: Starting all the queues");
		registry.getListenerContainers()
		.forEach(container -> {
			logger.info("startAllQueues: starting container " + container);
			container.start();
		});
	}
}
