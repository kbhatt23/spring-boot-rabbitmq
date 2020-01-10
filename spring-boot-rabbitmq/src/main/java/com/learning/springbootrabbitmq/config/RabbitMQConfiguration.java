package com.learning.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


	//we can still send without below configuration bean
	//however without below the bean data will not be visible in rabbit mq admin
	//it will look encrypted or serialized
	//with help of below config we can see bean clearly
	   @Bean
	    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	        return rabbitTemplate;
	    }

	    @Bean
	    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	        return new Jackson2JsonMessageConverter();
	    }
	    
	    @Bean
	    public Queue message1Queue() {
	    	return new Queue(RabbitMQMessageConstants.MESSAGE_TYPE1_QUEUE);
	    }
	    
	    //i am excepting type 1 message to be direct, which means routingid(provided during convertandsendfunction)=binding id
	    @Bean
	    public DirectExchange exchangeType1() {
	    	return new DirectExchange(RabbitMQMessageConstants.EXCHANGE_TYPE_1);
	    }
	    
	    @Bean
	    public Binding type1Binding() {
	    	return BindingBuilder.bind(message1Queue()).to(exchangeType1()).with(RabbitMQMessageConstants.ROUTING_KEY_TYPE1);
	    
	    }
	    
	/*
	 * @Bean public DirectExchange exchangeTypeGeneric() { return new
	 * DirectExchange(RabbitMQMessageConstants.EXCHANGE_TYPE_GENERIC); }
	 * 
	 * @Bean public Queue messageGenericQueue() { return new
	 * Queue(RabbitMQMessageConstants.MESSAGE_GENERIC_QUEUE); }
	 * 
	 * @Bean public Binding typeGenericBinding() { return
	 * BindingBuilder.bind(messageGenericQueue()).to(exchangeTypeGeneric()).with(
	 * RabbitMQMessageConstants.ROUTING_KEY_GENERIC);
	 * 
	 * }
	 */
}
