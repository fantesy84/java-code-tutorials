/**
 * Project multithread-question2
 * File: BankAccount.java
 * CreateTime: 2016年1月26日
 * Creator: junjie.ge
 * copy right ©2016 葛俊杰
 */
package net.fantesy84.domain;

/**
 * TypeName: BankAccount
 * <P>TODO
 * 
 * <P>CreateTime: 2016年1月26日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class BankAccount {
	private String id;
	private Double amount;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
