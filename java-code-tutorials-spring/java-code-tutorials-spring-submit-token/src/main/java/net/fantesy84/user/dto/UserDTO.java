/**
 * 项目名: java-code-tutorials-spring-submit-token
 * 包名:  net.fantesy84.user.dto
 * 文件名: UserDTO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月25日
 */
package net.fantesy84.user.dto;

import java.util.List;

import net.fantesy84.sys.dto.BaseTokenDTO;
import net.fantesy84.user.domain.User;

/**
 * @author Andronicus
 * @since 2015年12月25日
 */
public class UserDTO extends BaseTokenDTO {
	private static final long serialVersionUID = -2323880231788528738L;
	private String code;
	private String message;
	private List<User> users;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
