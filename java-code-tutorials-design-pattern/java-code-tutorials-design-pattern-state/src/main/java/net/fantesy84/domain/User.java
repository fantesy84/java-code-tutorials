/**
 * 项目名: java-code-tutorials-design-pattern-state
 * 包名:  net.fantesy84.domain
 * 文件名: User.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class User implements Serializable {
	private static final long serialVersionUID = -2606994996492409610L;
	private Integer id;
	private String account;
	private String passwd;
	private List<Role> roles;
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
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
