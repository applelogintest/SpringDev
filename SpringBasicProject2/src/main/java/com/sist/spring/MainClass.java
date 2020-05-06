package com.sist.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
					new ClassPathXmlApplicationContext("app.xml");
		
		SawonContainer sc=(SawonContainer)app.getBean("sc"); // DL
		sc.display();
	}

}
