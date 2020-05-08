package com.sist.temp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
/*
 	app
	============================
		key		  class 주소
	============================
	  boardDAO       100
	============================
	  noticeDAO      200
	============================
	  mainClass      300 ==> MyDAO
	============================
 */
@Component
public class MainClass {
	/*@Autowired // Autowired는 private변수는 setter없이 제어가능 OOP가 제대로 되지않는다고 지적이 나옴 
	@Qualifier("boardDAO")*/
	@Resource(name="noticeDAO")
	private MyDAO dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
					new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		/*MainClass mc1=new MainClass();
		mc1.dao.insert();*/
		
		mc.dao.select();
	}

}
