package com.jachs.spring_core.bean.list;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.jachs.spring_core.bean.Person;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Aggregate {

	private String[] strList=new String[5];
	private int[] intList=new int[5];
	
	private List<String>sList;
	private List<Person>perList;
	
	private Set<String>setSet;
	private Set<Person>perSet;
	
	private Map<String,Integer>map;
	
	private Properties pro;
	
	public Aggregate() {
		super();
	}

	public Aggregate(String[] strList, int[] intList, List<String> sList, List<Person> perList, Set<String> setSet,
			Set<Person> perSet, Map<String, Integer> map, Properties pro) {
		super();
		this.strList = strList;
		this.intList = intList;
		this.sList = sList;
		this.perList = perList;
		this.setSet = setSet;
		this.perSet = perSet;
		this.map = map;
		this.pro = pro;
	}

	public String[] getStrList() {
		return strList;
	}

	public void setStrList(String[] strList) {
		this.strList = strList;
	}

	public int[] getIntList() {
		return intList;
	}

	public void setIntList(int[] intList) {
		this.intList = intList;
	}

	public List<String> getsList() {
		return sList;
	}

	public void setsList(List<String> sList) {
		this.sList = sList;
	}

	public List<Person> getPerList() {
		return perList;
	}

	public void setPerList(List<Person> perList) {
		this.perList = perList;
	}

	public Set<String> getSetSet() {
		return setSet;
	}

	public void setSetSet(Set<String> setSet) {
		this.setSet = setSet;
	}

	public Set<Person> getPerSet() {
		return perSet;
	}

	public void setPerSet(Set<Person> perSet) {
		this.perSet = perSet;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public Properties getPro() {
		return pro;
	}

	public void setPro(Properties pro) {
		this.pro = pro;
	}
	
}
