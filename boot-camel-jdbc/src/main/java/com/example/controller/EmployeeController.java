package com.example.controller;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employeess;

@RestController

public class EmployeeController {
	
	@Autowired
	ProducerTemplate producerTemplate;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employeess> getAllEmployees() {
		List<Employeess> employees = producerTemplate.requestBody("direct:select", null, List.class);
		return employees;

	}

	@RequestMapping(value = "/employees", consumes = "application/json", method = RequestMethod.POST)
	public boolean insertEmployee(@RequestBody Employeess emp) {
		producerTemplate.requestBody("direct:insert", emp, List.class);
		return true;
	}

}
