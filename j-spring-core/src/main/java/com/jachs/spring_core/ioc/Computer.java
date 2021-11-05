package com.jachs.spring_core.ioc;

import com.jachs.spring_core.ioc.iface.Mouse;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Computer {
	private Mouse mouse;

	public Computer() {
		super();
	}

	public Computer(Mouse mouse) {
		super();
		this.mouse = mouse;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	
	
}
