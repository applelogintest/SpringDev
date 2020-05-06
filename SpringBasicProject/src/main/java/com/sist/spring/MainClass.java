package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {
	
	public static void main(String[] args)
	{
		// XML 파싱
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		
		// 등록된 클래스를 사용
		/*
		 *  class A
		 *  
		 *  A a=new A();
		 *  	=> A b=a; //싱글톤
		 *  A b=new A();
		 */
		Sawon sa=(Sawon)app.getBean("sa5"); // DL+DI=DI(IoC)
		System.out.println("sa="+sa);
		System.out.println("사번:"+sa.getSabun());
		System.out.println("이름:"+sa.getName());
		System.out.println("부서:"+sa.getDept());
		/*sa.display();*/
		//sa.setSabun(1);
		//sa.setName("홍길동");
		//sa.setDept("개발부");
		
		//Sawon sa1=(Sawon)app.getBean("sa");
		//sa1.setSabun(2);
		//sa1.setName("심청이");
		//sa1.setDept("영업부");
		
		//System.out.println(sa.getName()+" ");
		//System.out.println(sa1.getName()+" ");
		//sa1.display();
	}
}
