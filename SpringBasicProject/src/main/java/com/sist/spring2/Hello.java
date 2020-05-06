package com.sist.spring2;

// 인터페이스 안에서 구현가능 한 메소드 (자바 1.8버전부터)
// default static 을 사용하면 가능 
// 인터페이스는 implements 받은 클래스에 반드시 메소드를 구현을 해줘야 에러가안난다.
// 하지만 인터페이스에서 미리 구현해놓으면 영향력을 안줄수 있다.
public interface Hello {
	public void display();
	public default void sayHello(){}
	public static void sayHello2(){}
}
