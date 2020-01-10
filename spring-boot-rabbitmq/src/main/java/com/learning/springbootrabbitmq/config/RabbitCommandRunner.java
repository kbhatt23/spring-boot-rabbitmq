package com.learning.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import com.learning.springbootrabbitmq.CustomMessage;

@Configuration
public class RabbitCommandRunner {
	@Autowired
	private RabbitTemplate defaultRabbitTemplate;
	
	    
	    

	@Bean
	CommandLineRunner rabbitRunner() {
	
		return (args) -> {
			System.out.println("Command line started");
			//we have to manually create queue with name testQueue 
			//if @bean Querue with below name is not defined
		//	defaultRabbitTemplate.convertAndSend("testQueue","jai ram duta");
			
			//default exchange is direct
			//defaultRabbitTemplate.convertAndSend("exchangeQueue2", "queue2Route", "jai mahabali hanuman");
			//CustomMessage message = new CustomMessage("hanuman", "best and most powerful ever", true, "commandLine");
			//defaultRabbitTemplate.convertAndSend("exchangeQueue2", "queue2Route", message);
		};
	}
}
