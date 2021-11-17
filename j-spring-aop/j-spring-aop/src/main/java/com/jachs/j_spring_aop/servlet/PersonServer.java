package com.jachs.j_spring_aop.servlet;

import com.jachs.j_spring_aop.entity.Person;

/***
 * 
 * @author zhanchaohan
 *
 */
public interface PersonServer {

	void save(Person person);

	void query(Person person);

}
