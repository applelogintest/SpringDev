package com.sist.spring;
/*
 	DAO
 	
 	insert() DAO
 	
 	update() DAO
 	
 	delete() DAO
 	
 	select() DAO
 	
 	detail() DAO
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;
import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private MemberDAO dao;
	public static void main(String[] args) {
		ApplicationContext app=
					new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mainClass");
		
		List<MemberVO> list=mc.dao.memberAllData();
		for(MemberVO vo:list)
		{
			System.out.println(vo.getNo()+" "+vo.getName()+" "
							+vo.getTel()+" "+vo.getSex()
							+" "+vo.getAddr());
			
		}
	}

}
