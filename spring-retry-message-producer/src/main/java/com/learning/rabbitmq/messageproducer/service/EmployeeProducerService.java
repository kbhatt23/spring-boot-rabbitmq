package com.learning.rabbitmq.messageproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.messageproducer.entity.Employee;
import com.learning.rabbitmq.messageproducer.entity.EmployeeDTO;
import com.learning.rabbitmq.messageproducer.entity.EmployeeSerializable;

@Service
public class EmployeeProducerService extends RabbitUsageService{
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(EmployeeProducerService.class);
	private int id = 1;
	private int age =101;
	private static final String EMPLOYEE_EXCHANGE = "employee.exchange";
	
	public void generateEmployee(EmployeeDTO employeeDTO) throws JsonProcessingException {
		Employee employee = objectMapper.convertValue(employeeDTO, Employee.class);
		String routingKey = "employee-queue";
		logger.info("generateEmployee: Producing employee "+employee +" to queue "+routingKey);
		//as of now just ocncerting to string using jackson so that same format will be used by reicever
		String writeValueAsString = objectMapper.writeValueAsString(employee);
		getRabbitTemplate().convertAndSend(routingKey, writeValueAsString);
		
	}
	
	public void generateEmployeeSerilization(EmployeeDTO employeeDTO) throws JsonProcessingException {
		EmployeeSerializable employee = objectMapper.convertValue(employeeDTO, EmployeeSerializable.class);
		String routingKey = "employee-queue-serializable";
		logger.info("generateEmployeeSerilization: Producing employee "+employee +" to queue "+routingKey);
		//this method is using serilazation ot send obj in json format directly
		//the bean annotation in jacksonconfig saves time to re/write using mapper class
		//String writeValueAsString = objectMapper.writeValueAsString(employee);
		getRabbitTemplate().convertAndSend(routingKey, employee);
		
	}
	
	public void generateEmployeeSerilizationUsingExcahnge(EmployeeDTO employeeDTO) throws JsonProcessingException {
		EmployeeSerializable employee = objectMapper.convertValue(employeeDTO, EmployeeSerializable.class);
		String routingKey = "employee-route-serializable";
		logger.info("generateEmployeeSerilization: Producing employee "+employee +" to queue "+routingKey);
		//this method is using serilazation ot send obj in json format directly
		//the bean annotation in jacksonconfig saves time to re/write using mapper class
		//String writeValueAsString = objectMapper.writeValueAsString(employee);
		getRabbitTemplate().convertAndSend(EMPLOYEE_EXCHANGE,routingKey, employee);
		
	}
	
	//every half a second produces
	//consumer is slow, hence we added concurrnecy of 2 there
	//@Scheduled(fixedDelay = 1000)
	public void scheyudleEmployee() {
		EmployeeDTO  emp = new EmployeeDTO("sita ram", age, 108.108d, "Emp-"+String.valueOf(id));
		try {
			//generateEmployee(emp);
			generateEmployeeSerilization(emp);
			id++;age++;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
