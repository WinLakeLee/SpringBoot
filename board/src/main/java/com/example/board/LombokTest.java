package com.example.board;

public class LombokTest {

	public static void main(String[] args) {
		
		LombokClass lc =  new LombokClass("임꺽정", 19, "서울");
		
		LombokClass lcBuild = LombokClass.builder()
										 .name("홍길동")
										 .age(45)
										 .build();
		
		System.out.println(lc.getName());
		System.out.println(lc.getAge());
		System.out.println(lc.getAddress());
		
		System.out.println(lcBuild.getName());
		System.out.println(lcBuild.getAge());
		System.out.println(lcBuild.getAddress());
		
	}
}
