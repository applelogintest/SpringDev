package com.sist.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
/*
 	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		p:configLocation="classpath:Config.xml" 
	/>
	 @Autowired => 자동주입 (클래스의 메모리 주소만 주입) 일반변수는 불가능 => 스프링에 등록된 클래스만 사용가능
	 @ => TYPE (클래스)
	 public class A
	 {
	 	@
	 	MyDAO dao; => FIELD
	 	
	 	@
	 	public A(MyDAO dao){} => CONSTRUCTOR
	 	
	 	@
	 	public void setMyDAO(MyDAO dao){} => PARAMETER
	 	
	 	@
	 	public void display(MyDAO dao){} 
	 }
	 
	 => 클래스 메모리 할당 (클래스 관리자)
	 => 멤버 변수에 값을 주입
	 => 클래스의 메모리 해제
	 => 반복적인 코딩을 제거 => 소스 간결화, 가독성, 공통성
 */
import java.sql.*;
import java.util.*;

@Component("ssf")
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	
	/*@Autowired Spring에 등록을 해서 메모리가 할당된 클래스들 중에 자동으로 값을 찾아서 주소값을 넘겨주는 어노테이션
	@Qualifier("ds")*/
	//@Resource(name="ds")
	@Autowired
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
	
	public MySqlSessionFactoryBean(){
		try{
			org.springframework.core.io.Resource res=
						new ClassPathResource("Config.xml");
			//java.util.Date date=new java.util.Date();
			
			setConfigLocation(res);
		}catch (Exception ex) {}
		
	}
	
}














