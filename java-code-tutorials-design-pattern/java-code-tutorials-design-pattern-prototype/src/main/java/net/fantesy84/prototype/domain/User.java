/**
 * 项目名: java-code-tutorials-design-pattern-prototype
 * 包名:  net.fantesy84.prototype.domain
 * 文件名: User.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.prototype.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class User implements Serializable,Cloneable {
	private static final long serialVersionUID = -5950889974589633578L;
	private Integer id;
	private int sort;
	private String account;
	private String password;
	private String realName;
	private String nickName;
	private Date birthday;
	private Boolean deleteFlag;
	
	@Override
	public User clone() throws CloneNotSupportedException {
		return (User) super.clone();
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the deleteFlag
	 */
	public Boolean getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
