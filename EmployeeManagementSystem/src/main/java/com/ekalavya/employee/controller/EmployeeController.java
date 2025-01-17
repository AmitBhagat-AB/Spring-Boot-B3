package com.ekalavya.employee.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.employee.model.Employee;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;
import com.ekalavya.employee.model.EmployeeResponseModel;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@GetMapping(path = "/details", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Employee> getEmployee() {
		Employee employee = new Employee(100, "Harsh Thakur", "Indore", "Madhya Pradesh");
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return response;
	}
	
	@GetMapping(path = "/all", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> listEmployee =  new ArrayList<>();
		
		Employee employee1 = new Employee(100, "Harsh Thakur", "Indore", "Madhya Pradesh");
		Employee employee2 = new Employee(101, "Monit", "Bhopal", "Madhya Pradesh");
		Employee employee3 = new Employee(102, "Aishwarya", "Indore", "Madhya Pradesh");
		Employee employee4 = new Employee(103, "Amit Bhagat", "Pune", "Maharastra");
		
		listEmployee.add(employee1);
		listEmployee.add(employee2);
		listEmployee.add(employee3);
		listEmployee.add(employee4);
		
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<EmployeeResponseData> createEmployee(@RequestBody EmployeeRequestModel request){
		
		EmployeeResponseData data = new EmployeeResponseData();
		EmployeeResponseModel response = new EmployeeResponseModel();
		
		BeanUtils.copyProperties(request, response);
		
		String referenceNumber = UUID.randomUUID().toString();
		
		response.setEmployeeReferenceNumber(referenceNumber);
		
		response.setEmployeeName(request.getFirstName()+" "+request.getLastName());
		
		data.setDatails(response);
		
		ResponseEntity<EmployeeResponseData> responseEntity =  new ResponseEntity<EmployeeResponseData>(data, HttpStatus.OK);
		
		return responseEntity;
		
	}
}
