package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class TestController {
	
	@RequestMapping("/home")
	public Employee firstPage() {

		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		System.err.println("employee:"+emp);

		return emp;
	}
	
	@RequestMapping("/app")
	@ResponseBody
	public String data() {
		return "Hello";
	}


}
