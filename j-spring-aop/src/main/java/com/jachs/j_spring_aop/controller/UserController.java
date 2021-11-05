package com.jachs.j_spring_aop.controller;

import com.jachs.j_spring_aop.servlet.UserServer;

/***
 * 
 * @author zhanchaohan
 *
 */
public class UserController {
	private UserServer userServer;
	
	public void save() {
		System.out.println("UserController:save");
		userServer.save();
	}
	public void update() {
		System.out.println("UserController:update");
		userServer.update();
	}
	public void delete() {
		System.out.println("UserController:delete");
		userServer.delete();
	}
	public void query() {
		System.out.println("UserController:query");
		userServer.query();
	}
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	
}
