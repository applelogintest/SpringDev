package com.sist.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

/*
 	@Component @Repository
 	  주입(DI)
 */

@Component
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	// init-method
	@PostConstruct
	public void init()
	{
		System.out.println("=========== 사원 정보 ============");
	}
	
	//destroy-method
	@PreDestroy
	public void destory()
	{
		System.out.println("=========== 프로그램 종료 ============");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext app=
					new GenericXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		System.out.println(mc.dao);
		
		EmpVO vo=mc.dao.empFindData(7788);
		System.out.println("사번 :"+vo.getEmpno());
		System.out.println("이름 :"+vo.getEname());
		System.out.println("직위 :"+vo.getJob());
		System.out.println("입사일 :"+vo.getHiredate());
		System.out.println("급여 :"+vo.getSal());
		
		app.close();
	}

}
