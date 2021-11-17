package com.jachs.aspectj.part2;


/***
 * 
 * @author zhanchaohan
 *
 */
public class Human implements Behaved{
	private String name;
	
	public Human(String name) {
		super();
		this.name = name;
	}
	
	public void breakFast() {
		System.out.println(this.name+"开始吃早饭");
	}

	public void wakeUp() {
		System.out.println(this.name+"睡醒");
	}

	public void sleep() {
		System.out.println(this.name+"开始睡觉");
	}

	public void breakFast(boolean isBanan) {
		if(isBanan) {
			System.out.println("吃香蕉");
		}else {
			System.out.println("吃空气");
		}
	}
}
