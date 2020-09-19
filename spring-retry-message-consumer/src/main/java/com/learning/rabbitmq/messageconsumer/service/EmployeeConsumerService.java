package com.learning.rabbitmq.messageconsumer.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.rabbitmq.messageconsumer.entity.Employee;
import com.learning.rabbitmq.messageconsumer.entity.EmployeeSerializable;

//@Service
public class EmployeeConsumerService {
	Logger logger = LoggerFactory.getLogger(EmployeeConsumerService.class);
	private ObjectMapper mapper = new ObjectMapper();
	// public static final String coreProcessors =
	// String.valueOf(Runtime.getRuntime().availableProcessors());

	// shud use concurrnecy as time consuming task and hence another request of
	// another employee will wait until this gets over
	// @RabbitListener(queues = { "employee-queue" }, concurrency = "2")
	// public void fetchEmployeeAndCreateInDB(Employee employee) {
	public void fetchEmployeeAndCreateInDB(String employeeStr)
			throws JsonParseException, JsonMappingException, IOException {
		Employee employee = mapper.readValue(employeeStr, Employee.class);
		logger.info("fetchEmployeeAndCreateInDB:Reciveded Employee " + employee);
		// time consuming task
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		logger.info("fetchEmployeeAndCreateInDB:Saved Employee in DB " + employee);
	}

	@RabbitListener(queues = { "employee-queue-serializable" }, concurrency = "2")
	// public void fetchEmployeeAndCreateInDB(Employee employee) {
	public void fetchEmployeeSerializableAndCreateInDB(EmployeeSerializable employee)
			throws JsonParseException, JsonMappingException, IOException {
		// Employee employee = mapper.readValue(employeeStr, Employee.class);
		logger.info("fetchEmployeeSerializableAndCreateInDB Employee " + employee);
		// time consuming task
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		if (!employee.getName().toLowerCase().contains("ram")) {
			throw new IllegalArgumentException("Employee nam must contain ram bhagwan's name");
		}
		logger.info("fetchEmployeeSerializableAndCreateInDB:Saved Employee in DB " + employee);
	}

	@RabbitListener(queues = { "employee-deadletter-queue" })
	// public void fetchEmployeeAndCreateInDB(Employee employee) {
	public void deadLetterEmployee(EmployeeSerializable employee) {
		logger.info("deadLetterEmployee: Sending email to admin for message " + employee);
	}

}
