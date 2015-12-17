/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.domain
 * 文件名: Customer.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.domain;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = -58426270907074291L;
	private Long id;
	private String customerNo;
	private Integer points;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the customerNo
	 */
	public String getCustomerNo() {
		return customerNo;
	}
	/**
	 * @param customerNo the customerNo to set
	 */
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
