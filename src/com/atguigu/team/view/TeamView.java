package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;

public class TeamView {

	private NameListService listSvc=new NameListService();
	private TeamService teamSvc = new TeamService();
	
	//进入主界面
	public void enterMainMenu() {
		boolean loopFlag = true;
		char menu=0;
		while(loopFlag) {
			if(menu!='1') {
			listAllEmployees();
			}
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			
			menu=TSUtility.readMenuSelection();
			switch(menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.println("确认是否退出(Y/N):");
				char isExit=TSUtility.readConfirmSelection();
				if(isExit=='Y') {
					loopFlag=false;
				}
				break;
			}
		}
	}
	
	//显示所有成员信息
	public void listAllEmployees() {
	
	System.out.println("-------------------------------开发团队调度软件--------------------------------\n");	
	Employee[] emps = listSvc.getAllEmployees();
	if (emps.length == 0) {
		System.out.println("没有员工记录！");
	} else {
		System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
	}
		
	for(int i=0;i<emps.length;i++) {
			System.out.println(emps[i]);
		}
	System.out.println("-------------------------------------------------------------------------------");
	
	}
	
	//显示开发团队成员列表的操作
	public void getTeam() {
		System.out.println("--------------------团队成员列表---------------------\n");
		
		Programmer[] team = teamSvc.getTeam();
		if (team.length == 0) {
			System.out.println("开发团队目前没有成员！");
		} else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");			
			for(int i=0;i<team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
		System.out.println("-----------------------------------------------------");
	}
	
	//实现添加成员的操作
	public void addMember() {		
		System.out.println("---------------------添加成员---------------------");
		System.out.print("请输入要添加的员工ID：");
		int id=TSUtility.readInt();
		
		try {
			Employee emp=listSvc.getEmployee(id);
			teamSvc.addMember(emp);
			//没有异常时
			System.out.println("添加成功");
			TSUtility.readReturn();
		} catch (TeamException e) {
			//存在异常
			System.out.println("添加失败，原因："+e.getMessage());		
		}
	
	}
	
	//实现删除成员的操作
	public void deleteMember() {
		System.out.println("---------------------删除成员---------------------");
		System.out.print("请输入要删除员工的TID：");
		
		int memberId=TSUtility.readInt();
		System.out.print("确认是否删除(Y/N):");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete=='N') {
			return;
		}
		try {
			teamSvc.removeMember(memberId);
			System.out.println("删除成功");
		} catch (TeamException e) {
			System.out.println("删除失败，原因：" + e.getMessage());			
		}
		
		//按回车继续...
		TSUtility.readReturn();
	}
	
	//main方法
	public static void main(String[] args) {
		TeamView view=new TeamView();
		view.enterMainMenu();
	}
}
