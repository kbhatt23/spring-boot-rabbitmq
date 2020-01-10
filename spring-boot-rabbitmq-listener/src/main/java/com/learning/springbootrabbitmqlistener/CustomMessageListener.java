package com.learning.springbootrabbitmqlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.learning.springbootrabbitmqlistener.RabbitMQMessageConstants;

@Service
public class CustomMessageListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMessageListener.class);


    @RabbitListener(queues = "testQueue1")
    public void receiveMessage(final CustomMessage customMessage) {
        log.info("receiveMessage:Received message as specific class: {}", customMessage.toString());
    }
    
    @RabbitListener(queues = RabbitMQMessageConstants.MESSAGE_TYPE1_QUEUE)
    public void receiveMessage1(final CustomMessage customMessage) {
    	 log.info("receiveMessage1: Received message as specific class: {}", customMessage.toString());
    }
    
	/*
	 * @RabbitListener(queues = RabbitMQMessageConstants.MESSAGE_GENERIC_QUEUE)
	 * public void receiveGenericMessage(final Message message) {
	 * log.info("receiveGenericMessage:Received message as specific class: {}",
	 * message.getBody().toString()); }
	 */
    
    }