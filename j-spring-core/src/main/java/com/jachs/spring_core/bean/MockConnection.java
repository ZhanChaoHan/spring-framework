package com.jachs.spring_core.bean;

/**
 * 
 * @author zhanchaohan
 *
 */
public class MockConnection {
	private People people;

	public MockConnection() {
		super();
	}

	public MockConnection(People people) {
		super();
		this.people = people;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public void initConnection() {
		System.out.println(this.people.getName() + "initConnection");
	}

	public void destoryConnection() {
		System.out.println(this.people.getName() + "destoryConnection");
	}
}
