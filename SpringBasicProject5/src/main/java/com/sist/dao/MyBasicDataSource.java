package com.sist.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;
/*
 	클래스 메모리할당 할때 사용되는 어노테이션
 * 	@Component : 일반 클래스
    @Repository : DAO, 데이터베이스 관련
    @Service : BI => DAO + DAO => Service VS DAO 의차이 여러개의 DAO를 묶어서 사용할때 Service DAO 하나의 DAO를 사용
    @Controller : Model (JSP파일명)
    @RestController : Restful ==> 필요한 데이터 전송(AJAX) , XML,JSON 
    @ControllerAdvice : AOP
 */
/*
 	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
		p:driverClassName="oracle.jdbc.driver.OracleDriver"
		p:url="jdbc:oracle:thin:@localhost:1521:XE"
		p:username="hr"
		p:password="happy"
		p:maxActive="20"
		p:maxIdle="10"
		p:maxWait="-1"
	/>
 */
@Component("ds")
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource()
	{
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		setUsername("hr");
		setPassword("happy");
		setMaxActive(20);
		setMaxIdle(10);
		setMaxWait(-1);
	}
}
