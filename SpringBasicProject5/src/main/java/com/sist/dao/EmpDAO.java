package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

// mybatis 스프링 연동
@Repository("eDao")
// <bean id="eDao" class="com.sist.dao.EmpDAO">
// ID를 지정안하면 자동 ID 생성=> empDAO (맨 앞의 글자만 소문자로 바껴서 생김)
// MainClass => mainClass
public class EmpDAO extends SqlSessionDaoSupport{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public List<EmpVO> empAllData(){
		return getSqlSession().selectList("empAllData");
	}
	/*
	 	try
	 	{
	 		session=ssf.openSession();
	 		session.selectList("empAlldata");
	 	}catch(Exception ex)
	 	{
	 	}
	 	fianlly
	 	{
	 		if(session!=null)
	 			session.close();
	 	}
	 */
	public EmpVO empFindData(int empno){
		return getSqlSession().selectOne("empFindData",empno);
	}
	
}
