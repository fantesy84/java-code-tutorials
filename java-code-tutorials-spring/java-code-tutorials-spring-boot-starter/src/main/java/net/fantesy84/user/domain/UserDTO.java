/**
 * 项目名: java-code-tutorials-spring-boot-starter
 * 包名:  net.fantesy84.user.domain
 * 文件名: UserDTO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.user.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 5113491204127997187L;
	private String statusCode;
	private String msg;
	private List<User> users;
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
