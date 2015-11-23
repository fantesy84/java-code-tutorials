/**
 * 项目名: java-code-tutorials-design-pattern-builder
 * 包名:  net.fantesy84.builder.domain
 * 文件名: Food.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.builder.domain;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public abstract class Food implements Serializable {
	private static final long serialVersionUID = -3399238758950156164L;
	private String name;
	private Double price;
	
	/**
	 * 
	 */
	public Food() {
		super();
	}
	
	/**
	 * @param name
	 * @param price
	 */
	public Food(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	protected abstract Double disaccount(Integer integral);

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
