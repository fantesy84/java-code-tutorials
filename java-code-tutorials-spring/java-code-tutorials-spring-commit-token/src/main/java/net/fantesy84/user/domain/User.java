/**
 * Project java-code-tutorials-spring-commit-token
 * File: User.java
 * CreateTime: 2015年12月23日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TypeName: User
 * 
 * <P>CreateTime: 2015年12月23日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class User extends BaseDTO {
	private static final long serialVersionUID = -7373866585364487197L;
	private Integer id;
	private String name;
	private String sex;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8.00")
	private Date birthday;
	
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param birthday
	 */
	public User(String name, String sex, Date birthday) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
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
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	
}
