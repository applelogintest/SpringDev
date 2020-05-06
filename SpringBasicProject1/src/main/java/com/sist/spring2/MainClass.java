package com.sist.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx=
				new AnnotationConfigApplicationContext(Config.class);
		EmpDAO dao=ctx.getBean("empDAO",EmpDAO.class); // 제네릭스 or 앞에 형변환을 해줘야 받을수 있다
		
		List<EmpVO> list=dao.empAllData();
		
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEname()+" "+vo.getJob()+" "+vo.getSal());
		}
	}

}
