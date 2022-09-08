package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employeess;

@Service
public class EmployeeServiceImpl extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		}

	@Override
	public void configure() throws Exception {
        
	       	 //Insert Route
		from("direct:insert").process(new Processor() {
			public void process(Exchange xchg) throws Exception {
			//Take the Employee object from the exchange and create the insert query
				Employeess employees = xchg.getIn().getBody(Employeess.class);
				String query = "INSERT INTO employeess(empId,empName)values('" + employees.getEmpId() + "','"
						+ employees.getEmpName() + "')";
			// Set the insert query in body and call camel jdbc
				xchg.getIn().setBody(query);
			}
		}).to("jdbc:dataSource");
		
		// Select Route
		from("direct:select").setBody(constant("select * from Employee")).to("jdbc:dataSource")
				.process(new Processor() {
					public void process(Exchange xchg) throws Exception {
					   //the camel jdbc select query has been executed. We get the list of employees.
						ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) xchg.getIn()
								.getBody();
						List<Employeess> employees = new ArrayList<Employeess>();
						System.out.println(dataList);
						for (Map<String, String> data : dataList) {
							Employeess employeess = new Employeess();
							employeess.setEmpId(data.get("empId"));
							employeess.setEmpName(data.get("empName"));
							employees.add(employeess);
						}
						xchg.getIn().setBody(employees);
					}
				});

		
	}
}
