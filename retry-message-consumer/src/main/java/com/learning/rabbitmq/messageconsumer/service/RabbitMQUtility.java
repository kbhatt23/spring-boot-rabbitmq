package com.learning.rabbitmq.messageconsumer.service;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.rabbitmq.messageconsumer.entity.RabbitMQQueue;

@Service
public class RabbitMQUtility {
	@Autowired
	private RestTemplate template;

	@Scheduled(fixedRate = 5000)
	public void scheudleDirtyQueueEmail() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", createBasicAuthHeaders());

		HttpEntity<String> entityReq = new HttpEntity<String>("", headers);

		//ResponseEntity<RabbitMQQueue[]> forEntity = template.getForEntity("http://localhost:15672/api/queues", RabbitMQQueue[].class, entityReq);
		
		ResponseEntity<RabbitMQQueue[]> forEntity = template.exchange(
                "http://localhost:15672/api/queues", HttpMethod.GET, new HttpEntity<Object>(headers),
                RabbitMQQueue[].class);
		
		
		RabbitMQQueue[] bodyArray = forEntity.getBody();
		for(RabbitMQQueue queue : bodyArray) {
			System.err.println("found dirty queue with name "+queue.getQueueName()+" with size "+queue.getQueueLength());
		}
		
	}
	
	public String createBasicAuthHeaders() {
		// username:password for rabbitmq
		String auth = "guest:guest";
		return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
	}
}
