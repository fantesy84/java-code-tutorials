/**
 * 项目名: reverse-database-mybatis
 * 包名:  net.fantesy84.common.domain
 * 文件名: BaseDTO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月19日
 */
package net.fantesy84.common.domain;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author Andronicus
 * @since 2015年11月19日
 */
public abstract class BaseDTO implements Serializable {
	private static final long serialVersionUID = -7990160428958859647L;
	private String className;
	private Type classType;
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the classType
	 */
	public Type getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	protected void setClassType(Type classType) {
		this.classType = classType;
	}
	
}
