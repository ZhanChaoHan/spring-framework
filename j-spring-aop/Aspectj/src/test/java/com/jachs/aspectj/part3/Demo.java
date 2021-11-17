package com.jachs.aspectj.part3;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		Guest guest=new Guest("小龙虾");
		Guest guest1=new Guest("大龙虾");
		
		
		guest.takeAnWalk();
		guest1.takeAnWalk();
		
		guest.buyAnBread();
		guest1.buyAnBread();
		guest.buyAnBottleOfWine();
		guest.buyAnBread();
	}
}
