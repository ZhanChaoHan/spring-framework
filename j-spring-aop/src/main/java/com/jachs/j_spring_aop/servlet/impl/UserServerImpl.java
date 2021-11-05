package com.jachs.j_spring_aop.servlet.impl;

import com.jachs.j_spring_aop.dao.UserDao;
import com.jachs.j_spring_aop.servlet.UserServer;

/***
 * 
 * @author zhanchaohan
 *
 */
public class UserServerImpl implements UserServer {
	private UserDao  userDao;
	
	@Override
	public void save() {
		userDao.save();
	}

	@Override
	public void update() {
		userDao.update();
	}

	@Override
	public void delete() {
		userDao.delete();
	}

	@Override
	public void query() {
		userDao.query();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	
}
