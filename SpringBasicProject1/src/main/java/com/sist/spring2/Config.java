package com.sist.spring2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 파일 스프링 5.0 이상 버전부터 자바로만 이용해서 등록
@Configuration
public class Config {
	// 등록할 Class <bean id=empDAO"" class="com.sist.spring2.EmpDAO">
	@Bean
	public EmpDAO empDAO()
	{
		EmpDAO dao=new EmpDAO();
		dao.setDriverName("oracle.jdbc.driver.OracleDriver");
		dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dao.setUserName("hr");
		dao.setPassword("happy");
		dao.init();
		return dao;
	}
}
