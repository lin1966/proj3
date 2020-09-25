package com.atguigu.team.junit;

import org.junit.jupiter.api.Test;

import com.atguigu.team.domain.Employee;

import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;

public class NameListServiceTest {

	@Test
	public void testGetAllEmployees() {
		
		NameListService service=new NameListService();
		Employee[] employees=service.getAllEmployees();
		
		for(int i=0;i<employees.length;i++) {
			System.out.println(employees[i]);
		}
	}
	
	@Test
	public void testgetEmployee() {
		int id = 5;
		NameListService listService = new NameListService();
		try {
			Employee emp = listService.getEmployee(id);
			System.out.println(emp);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}
