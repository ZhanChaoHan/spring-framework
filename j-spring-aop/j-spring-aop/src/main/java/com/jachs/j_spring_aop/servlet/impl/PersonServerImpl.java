package com.jachs.j_spring_aop.servlet.impl;

import com.jachs.j_spring_aop.dao.PersonDao;
import com.jachs.j_spring_aop.entity.Person;
import com.jachs.j_spring_aop.servlet.PersonServer;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PersonServerImpl implements PersonServer {
	private PersonDao personDao;
	
	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public void query(Person person) {
		personDao.query(person);
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
