/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.user.dto
 * 文件名: ResBaseUserDTO.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月23日
 */
package net.fantesy84.user.dto;

import java.io.Serializable;

import net.fantesy84.user.domain.UserBase;

/**
 * @author Andronicus
 * @since 2016年2月23日
 */
public class ResUserBaseDTO implements Serializable {
	private static final long serialVersionUID = 8603753143558462965L;
	private String code;
	private String message;
	private UserBase base;
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
	 * @return the base
	 */
	public UserBase getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(UserBase base) {
		this.base = base;
	}
	
}
