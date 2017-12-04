package com.mch.yc.design.pattern.creation.builder.demo2;

import com.mch.yc.design.pattern.creation.builder.demo2.ConcreteBuilder;
import com.mch.yc.design.pattern.creation.builder.demo2.Director;
import com.mch.yc.design.pattern.creation.builder.demo2.Product;


/**
 * 
 * @author yc
 *
 */
public class ClientDemo2 {
	public static void main(String[] args) {
		
		Builder builder = new ConcreteBuilder();
		Director director = new Director(builder);
		director.construct();
		
		Product product = builder.retrieveResult();
		System.out.println(product.getPart1());
		System.out.println(product.getPart2());
	}
}
