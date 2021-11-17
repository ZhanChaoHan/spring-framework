package com.jachs.j_spring_aop.controller;

import com.jachs.j_spring_aop.entity.Person;
import com.jachs.j_spring_aop.servlet.PersonServer;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PersonController {
	private PersonServer personServer;

	public void save(Person person) {
		personServer.save(person);
	}

	public void query(Person person) {
		personServer.query(person);
	}


	public PersonServer getPersonServer() {
		return personServer;
	}

	public void setPersonServer(PersonServer personServer) {
		this.personServer = personServer;
	}
}
