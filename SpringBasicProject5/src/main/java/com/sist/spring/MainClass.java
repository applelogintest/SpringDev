package com.sist.spring;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component("mc")
public class MainClass {
	@Autowired
	private EmpDAO dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
					new ClassPathXmlApplicationContext("app.xml");
		
		MainClass mc=(MainClass)app.getBean("mc");
		List<EmpVO> list=mc.dao.empAllData();
		System.out.println(mc);
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getSal());
		}
		
		/*EmpDAO dao=(EmpDAO)app.getBean("empDAO");
		List<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}*/
	}

}
