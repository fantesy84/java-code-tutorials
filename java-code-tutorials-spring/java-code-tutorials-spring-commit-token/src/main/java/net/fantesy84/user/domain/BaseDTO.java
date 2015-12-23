/**
 * Project java-code-tutorials-spring-commit-token
 * File: BaseDTO.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.user.domain;

import java.io.Serializable;

/**
 * TypeName: BaseDTO
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月24日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = -379566853214685084L;
	private String token;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	protected void setToken(String token) {
		this.token = token;
	}
}
