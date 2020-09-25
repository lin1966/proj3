package com.atguigu.team.service;

import com.atguigu.team.domain.*;

import static com.atguigu.team.service.Data.*;

/*
 *	 功能：负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 */
public class NameListService {
	
	private Employee[] employees;
	
	//构造器
	public NameListService() {
//		根据项目提供的Data类构建相应大小的employees数组
//		再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象
//		将对象存于数组中
	
		employees=new Employee[EMPLOYEES.length];
		for(int i=0;i<employees.length;i++) {
			
			//获取员工的类型
			int type=Integer.parseInt(EMPLOYEES[i][0]);			
			//获取employee的四个基本信息
			int id=Integer.parseInt(EMPLOYEES[i][1]);
			String name=EMPLOYEES[i][2];
			int age=Integer.parseInt(EMPLOYEES[i][3]);
			double salary=Integer.parseInt(EMPLOYEES[i][4]);
			Equipment equipment;			
			double bonus;
			int stock;
			
			switch(type) {
			case EMPLOYEE:				
				employees[i]=new Employee(id, name, age, salary);
				break;
			case PROGRAMMER:
				equipment=createEquipment(i);
				employees[i]=new Programmer(id, name, age, salary, equipment);
				break;				
			case DESIGNER:
				equipment=createEquipment(i);
				bonus=Integer.parseInt(EMPLOYEES[i][5]);
				employees[i]=new Designer(id, name, age, salary, equipment, bonus);
				break;
			case ARCHITECT:
				equipment=createEquipment(i);
				bonus=Double.parseDouble(EMPLOYEES[i][5]);
				stock=Integer.parseInt(EMPLOYEES[i][6]);
				employees[i]=new Architect(id, name, age, salary, equipment, bonus, stock);
				break;			
			}
		}	
	}
	
	
	//将Equipment子类对象存于数组
	private Equipment createEquipment(int index) {
		int type = Integer.parseInt(EQUIPMENTS[index][0]);
		
		switch (type) {
		case PC://21
			return new PC(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
		case NOTEBOOK://22
			int price = Integer.parseInt(EQUIPMENTS[index][2]);
			return new NoteBook(EQUIPMENTS[index][1], price);
		case PRINTER://23
			return new Printer(EQUIPMENTS[index][1], EQUIPMENTS[index][2]);
		}		
		return null;
	}

	
	
	//获取当前所有员工
	public Employee[] getAllEmployees() {
		return employees;		
	}
	
	//获取指定ID的员工对象
	public Employee getEmployee(int id) throws TeamException {
		//方式一
//		public Employee getEmployee(int id) throws TeamException {
//			for (Employee e : employees) {
//				if (e.getId() == id)
//					return e;
//			}
//			throw new TeamException("该员工不存在");
//		}
		//方式二
		for(int i=0;i<employees.length;i++) {
			if(employees[i].getId()==id) {
				return employees[i];
			}
			
		}		
		throw new TeamException("找不到指定的员工");
	}
	
	
}
