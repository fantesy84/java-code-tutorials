/**
 * 项目名: java-code-tutorials-design-pattern-strategy
 * 包名:  net.fantesy84.domain
 * 文件名: Order.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月17日
 */
package net.fantesy84.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Andronicus
 * @since 2015年12月17日
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 3158058892919552984L;
	private Long id;
	private String serialNo;
	private BigDecimal totalAmount;
	private Customer customer;
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
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
