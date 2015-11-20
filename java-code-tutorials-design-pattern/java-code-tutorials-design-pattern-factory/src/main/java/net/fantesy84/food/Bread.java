/**
 * 项目名: java-code-tutorials-design-pattern-simplefactory
 * 包名:  net.fantesy84.simple.factory.food
 * 文件名: Bread.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月20日
 */
package net.fantesy84.food;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Andronicus
 * @since 2015年11月20日
 */
public class Bread implements Serializable {
	private static final long serialVersionUID = 210275785110406039L;
	private String name;
	private String taste;
	private Double price;
	private Date productionDate;
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
	 * @return the taste
	 */
	public String getTaste() {
		return taste;
	}
	/**
	 * @param taste the taste to set
	 */
	public void setTaste(String taste) {
		this.taste = taste;
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
	/**
	 * @return the productionDate
	 */
	public Date getProductionDate() {
		return productionDate;
	}
	/**
	 * @param productionDate the productionDate to set
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
}
