package com.jachs.aspectj.demo;


import org.springframework.stereotype.Component;

/***
 * 
 * @author zhanchaohan
 *
 */
@Component
public class HuMan {
	private String name="张三";
	private int age=15;
	
	public void Pc() {
		System.out.println("Pc");
	}
	public void openMouth() {
		System.out.println("openMouth");
	}
	public void speak() throws Exception {
		System.out.println("speak");
//		throw new Exception("异常出现");
	}
	public void speak(String word) {
		System.out.println(this.name+"speak："+word);
	}
}
