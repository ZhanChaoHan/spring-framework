package com.jachs.spring_core.bean;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Person {
	private String name;
	private int age;
	private long phoneNum;
	
	public Person(String name, int age, long phoneNum) {
		super();
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public long getPhoneNum() {
		return phoneNum;
	}
	
}
