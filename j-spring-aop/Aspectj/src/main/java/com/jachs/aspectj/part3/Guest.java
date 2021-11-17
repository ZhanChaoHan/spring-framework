package com.jachs.aspectj.part3;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Guest {
	private String name;

	private int monery = 100;// 初始化100块钱

	public Guest() {
		super();
	}

	public Guest(String name) {
		super();
		this.name = "客人:" + name;
	}

	public void takeAnWalk() {
		System.out.println(this.name + "出去走了走");
	}

	public void goHome() {
		System.out.println(this.name + "回家");
	}

	public void buyAnBread() throws Exception {
		if (monery >= 50) {
			this.monery = this.monery - 50;
			System.out.println(this.name + "买了个面包");
		} else {
			throw new Exception(this.name + "没钱买面包");
		}
	}

	public void buyAnBottleOfWine() throws Exception{
		if (monery >= 50) {
			this.monery = this.monery - 50;
			System.out.println(this.name + "买了瓶酒");
		} else {
			throw new Exception(this.name + "没钱买瓶酒");
		}
	}
}
