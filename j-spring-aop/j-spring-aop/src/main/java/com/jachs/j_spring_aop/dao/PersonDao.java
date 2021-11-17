package com.jachs.j_spring_aop.dao;

import com.jachs.j_spring_aop.entity.Person;

/***
 * 
 * @author zhanchaohan
 *
 */
public class PersonDao {

	public void save(Person person) {
		System.out.println("pName:\t"+person.getpName()+",pAge:\t"+person.getpAge()+",pEmail:\t"+person.geteMail());
	}

	public void query(Person person) {
		
	}

}
