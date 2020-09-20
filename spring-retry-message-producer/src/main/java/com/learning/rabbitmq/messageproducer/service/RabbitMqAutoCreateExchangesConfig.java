package com.learning.rabbitmq.messageproducer.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.rabbitmq.messageproducer.entity.MessageConstants;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMqAutoCreateExchangesConfig {

	@Bean
	public DirectExchange createDirectExcahnge() {
		return new DirectExchange(MessageConstants.CODE_BUILT_DIRECT_EXCHANGE);
	}
	
	@Bean
	public Queue createDirectQueue1() {
		return new Queue(MessageConstants.CODE_BUILT_DIRECT_QUEUE1);
	}
	
	@Bean
	public Queue createDirectQueue2() {
		return new Queue(MessageConstants.CODE_BUILT_DIRECT_QUEUE2);
	}
	
	@Bean
	public Binding createDirectExhcangeBinding1() {
		return BindingBuilder.bind(createDirectQueue1()).to(createDirectExcahnge()).with(MessageConstants.CODE_BUILT_DIRECT_ROUTE1);
	}
	@Bean
	public Binding createDirectExhcangeBinding2() {
		return BindingBuilder.bind(createDirectQueue2()).to(createDirectExcahnge()).with(MessageConstants.CODE_BUILT_DIRECT_ROUTE2);
	}
	
	//===========================fanout conifg=======================================
	
	@Bean
	public FanoutExchange createFanoutExcahnge() {
		return new FanoutExchange(MessageConstants.CODE_BUILT_FANOUT_EXCHANGE);
	}
	
	@Bean
	public Queue createFanoutQueue1() {
		return new Queue(MessageConstants.CODE_BUILT_FANOUT_QUEUE1);
	}
	
	@Bean
	public Queue createFanoutQueue2() {
		return new Queue(MessageConstants.CODE_BUILT_FANOUT_QUEUE2);
	}
	
	@Bean
	public Binding createFanoutExhcangeBinding1() {
		return BindingBuilder.bind(createFanoutQueue1()).to(createFanoutExcahnge());
	}
	@Bean
	public Binding createFanoutExhcangeBinding2() {
		return BindingBuilder.bind(createFanoutQueue2()).to(createFanoutExcahnge());
	}
}
