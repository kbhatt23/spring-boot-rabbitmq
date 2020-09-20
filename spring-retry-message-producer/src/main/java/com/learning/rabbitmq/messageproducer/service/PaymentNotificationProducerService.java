package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.learning.rabbitmq.entity.PaymentInfo;

//we need to ensure that this keeps on adding notification in queue
//even though during some scheudled timing we want to stop the consumer, however queue should alsways be open
//once schduerl restarts the consumer it can proceed with consumption
//@Service
public class PaymentNotificationProducerService extends RabbitUsageService {
	private Logger logger = LoggerFactory.getLogger(PaymentNotificationProducerService.class);
	private static final String FANOUT_EXCHANNGE_EXERCISE = "custom-fanout-exchange";

	private static int countId = 1;

	@Scheduled(fixedRate = 200000)
	//@Scheduled(fixedRate = 2000)
	public void addPaymentDetailsInQueue() {
		
		
		for (int a = 0; a < 500; a++) {
			PaymentInfo pi = new PaymentInfo(String.valueOf(countId), 100d + countId, "card-xxxx" + countId);
			//logger.info("addPaymentDetailsInQueue: Adding payment info in the queue " + pi);
			getRabbitTemplate().convertAndSend(FANOUT_EXCHANNGE_EXERCISE, "", pi);
			countId++;
		}
	}

}
