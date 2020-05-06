package com.sist.spring2;

public class HelloImp implements Hello{
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("HellowImp:display() Call...");
	}
	
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("HellowImp:display() Overriding...");
	}
}
