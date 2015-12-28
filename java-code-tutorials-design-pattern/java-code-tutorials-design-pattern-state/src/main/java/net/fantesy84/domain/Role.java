/**
 * 项目名: java-code-tutorials-design-pattern-state
 * 包名:  net.fantesy84.domain
 * 文件名: Role.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.domain;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 4448426809475813785L;
	private Integer id;
	private String roleCode;
	private String roleDesc;
	
	/**
	 * 
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param roleCode
	 */
	public Role(Integer id, String roleCode) {
		super();
		this.id = id;
		this.roleCode = roleCode;
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
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
