package com.jachs.spring_core.ioc.iface;

/**
 * 
 * @author zhanchaohan
 *
 */
public class MicrosoftMouse implements Mouse {

	public void click() {
		System.out.println("微软鼠标点击");
	}

	public void showInfo() {
		System.out.println("微软鼠标");
	}

}
