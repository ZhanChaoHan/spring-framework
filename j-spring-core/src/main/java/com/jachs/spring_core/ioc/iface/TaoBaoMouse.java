package com.jachs.spring_core.ioc.iface;

/**
 * 
 * @author zhanchaohan
 *
 */
public class TaoBaoMouse implements Mouse {

	public void click() {
		System.out.println("淘宝鼠标点击");
	}

	public void showInfo() {
		System.out.println("淘宝鼠标");
	}

}
