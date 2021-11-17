package com.jachs.aspectj.part3;

/***
 * @author zhanchaohan
 *
 */
public class Shopkeeper {
	private String name;
	private int monery=0;
	
	private static Shopkeeper chef;

	private Shopkeeper() {
		super();
	}

	private Shopkeeper(String name) {
		super();
		this.name = name;
	}


	public static Shopkeeper initChef(String name) {
		if(chef==null) {
			chef= new Shopkeeper(name);
		}
		return chef;
	}
	public void sellGoods(String goodsName) {
		System.out.println(this.name+"卖出了"+goodsName);
	}
	public void collection(int monery) {
		this.chef.monery=monery+this.chef.monery;
		System.out.println(this.name+"收款:"+monery+"共有:"+this.chef.monery);
	}
}
