package com.jachs.aspectj.part1;


/***
 * 
 * @author zhanchaohan
 *
 */
public class Human {
	private String name;
	
	public Human(String name) {
		super();
		this.name = name;
	}
	
	public void breakFast() {
		System.out.println(this.name+"开始吃早饭");
	}
}
