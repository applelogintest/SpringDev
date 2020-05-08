package com.sist.temp;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements MyDAO{
	@Override
	public void select() {
		// TODO Auto-generated method stub
		System.out.println("BoardDAO:select() Call..");
	}
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		System.out.println("BoardDAO:insert() Call..");
		
	}
}
