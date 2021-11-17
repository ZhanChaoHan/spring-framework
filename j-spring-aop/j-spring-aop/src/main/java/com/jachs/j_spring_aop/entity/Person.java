package com.jachs.j_spring_aop.entity;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Person {
	private String pName;
	private int pAge;
	private String eMail;
	
	public Person() {
		super();
	}
	public Person(String pName, int pAge, String eMail) {
		super();
		this.pName = pName;
		this.pAge = pAge;
		this.eMail = eMail;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpAge() {
		return pAge;
	}
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
}
