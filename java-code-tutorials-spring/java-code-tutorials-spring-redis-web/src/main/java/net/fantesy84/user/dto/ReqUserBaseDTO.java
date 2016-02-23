/**
 * 项目名: java-code-tutorials-spring-redis-web
 * 包名:  net.fantesy84.user.dto
 * 文件名: ReqUserBaseDTO.java
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
public class ReqUserBaseDTO implements Serializable {
	private static final long serialVersionUID = 2003513624571223452L;
	private UserBase base;
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
