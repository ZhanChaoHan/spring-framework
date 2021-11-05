package com.jachs.spring_core.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class Zoo {
	@Autowired
	private Tiger tiger;

	@Autowired
	private Monkey monkey;

	
	public void printTiger() {
		System.out.println(tiger.toString());
	}
	public void printMonkey() {
		System.out.println(monkey.toString());
	}
}