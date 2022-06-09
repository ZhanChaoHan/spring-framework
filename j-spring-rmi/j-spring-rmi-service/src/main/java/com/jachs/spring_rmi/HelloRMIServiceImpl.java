package com.jachs.spring_rmi;

/***
 * 
 * @author zhanchaohan
 *
 */
public class HelloRMIServiceImpl implements  HelloRMIService{

	@Override
	public int getAdd(int a, int b) {
		return a+b;
	}

}
