package com.learning.rabbitmq.messageproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.rabbitmq.messageproducer.entity.EmployeeDTO;
import com.learning.rabbitmq.messageproducer.service.EmployeeProducerService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeProducerService service;

	@PostMapping
	public ResponseEntity<EmployeeDTO> publishEmployee(@RequestBody EmployeeDTO employeeDTO) {
		try {
			service.generateEmployeeSerilizationUsingExcahnge(employeeDTO);
			return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.CREATED);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
