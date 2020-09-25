package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/*
 * 	关于开发团队成员的管理：添加、删除等
 * 
 */
public class TeamService {
	
	private static int counter=1;
	private final int MAX_MEMBER=5;
	private Programmer[] team=new Programmer[MAX_MEMBER];
	private int total;//记录开发团队中实际的人数
	
	public TeamService() {
		super();
	}
	
	
	//返回当前团队的所有对象
	public Programmer[] getTeam() {
		Programmer[] team=new Programmer[total];
		for(int i=0;i<team.length;i++) {
			team[i]=this.team[i];
		}
		return team;
	}
	
	
	
	//将指定id的员工添加到开发团队之中
	public void addMember(Employee e) throws TeamException {
//	失败信息包含以下几种：
//		成员已满，无法添加
		if(total>=MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加！");
		}
//		该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}		
//		该员工已在本开发团队中
		if(isExist(e)) {
			throw new TeamException("该员工已在本开发团队中");
		}
		
//		该员工已是某团队成员 		
//		该员正在休假，无法添加		
		Programmer p=(Programmer)e;
		if("BUSY".equals(p.getStatus().getNAME())) {
			throw new TeamException("该员工已在本开发团队中");
		}else if("VACATION".equals(p.getStatus().getNAME())) {
			throw new TeamException("该员正在休假，无法添加");
		}
		
//		团队中至多只能有一名架构师		
//		团队中至多只能有两名设计师
//		团队中至多只能有三名程序员 
		int numOfArch=0,numOfDes=0,numOfPro=0;
		for(int i=0;i<total;i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else if(team[i] instanceof Programmer){
				numOfPro++;
			}
		}	
		
		 if (p instanceof Architect) {
	            if (numOfArch >= 1) throw new TeamException("团队中至多只能有一名架构师");
	        } else if (p instanceof Designer) {
	            if (numOfDes >= 2) throw new TeamException("团队中至多只能有两名设计师");
	        } else if (p instanceof Programmer) {
	            if (numOfPro >= 3) throw new TeamException("团队中至多只能有三名程序员");
	        }
				
		//p的属性赋值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
		//将p（或者e）添加到现有的team中
		team[total++]=p;
			
	}
	
	private boolean isExist(Employee e) {
		for(int i=0;i<total;i++) {
			if(team[i].getId()==e.getId()) {
				return true;
			}
		}
		return false;
	}

	//从团队中删除成员
	public void removeMember(int memberId) throws TeamException {
		int i=0;
		for(;i<total;i++) {
			if(team[i].getMemberId()==memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
	//如果遍历一遍，都找不到指定ID，则报异常
		if (i == total)
			throw new TeamException("找不到该成员，无法删除");	
		
		//将删除成员id,后面的成员id往前移一位
		for(int j=i+1;j<total;j++) {
			team[j-1]=team[j];
		//最后成员id删除	
		team[--total]=null;
		}
	}
	
	
}
