package com.sist.aspect;

import java.security.Signature;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/*
	public void emp_select()
	{
		//getConnection();
		System.out.println("EMP 테이블에서 데이터를 가지고 온다");
		//disConnection();
	}
 */
@Aspect
@Component
public class MyAspect {
	//execution = 이 메소드가 호출될때 *는 리턴형 (*는 어떤 리턴형이 와도 받아줌 ) .. 매개변수가 없어도 되고 1개이상 즉 모든매개변수룰 받아줌
	/*
	 	* com.sist.dao.EmpDAO.emp_*(..)
	    = ============= 클래스명 ===== 시작하는 메소드 ..(매개변수는 관계없이)
	    return형                          매개변수 (X) , 매개변수(1), 매개변수(여러개..)
	 */
	@Before("execution(* com.sist.dao.EmpDAO.emp_*(..))")
	public void getConnection()
	{
		System.out.println("오라클 연결");
	}
	// finally
	@After("execution(* com.sist.dao.EmpDAO.emp_*(..))")
	public void disConnection()
	{
		System.out.println("오라클 연결 해제");
	}
	// 정상수행이 된 경우
	@AfterReturning(value="execution(* com.sist.dao.EmpDAO.emp_*(..))",returning="ret")
	// returning="ret" => 리턴값
	public void returnValue(Object ret)
	{
		if(ret!=null)
			System.out.println(ret);
	}
	
	// 메소드 수행하는 과정 => 오류가 발생할 경우에 처리
	@AfterThrowing(value="execution(* com.sist.dao.EmpDAO.emp_*(..))",throwing="ex")
	public void exceptionValue(Throwable ex)
	{
		System.out.println(ex.getMessage());
	}
	// log 파일 제작 , 트랜잭션 처리
	@Around("execution(* com.sist.dao.EmpDAO.display())")
	public Object display(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println(jp.getSignature().getName());
		long start=System.currentTimeMillis();
		// setAutoCommit(false)
		Object obj=jp.proceed(); // display 호출
		// conn.commit();
		long end=System.currentTimeMillis();
		System.out.println("수행시간 : "+(end-start));
		return obj;
	}
}
