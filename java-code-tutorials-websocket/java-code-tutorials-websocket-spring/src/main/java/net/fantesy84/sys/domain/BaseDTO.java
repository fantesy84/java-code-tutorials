/**
 * 项目名: java-code-tutorials-websocket-spring
 * 包名:  net.fantesy84.sys.domain
 * 文件名: BaseDTO.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月30日
 */
package net.fantesy84.sys.domain;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @author Andronicus
 * @since 2015年12月30日
 */
public abstract class BaseDTO implements Serializable, Cloneable {
	private static final long serialVersionUID = -2423164529812820142L;
	private String className;
	private Type classType;
	
	@SuppressWarnings("unchecked")
	public <T extends BaseDTO> T clone(Class<T> targetClass) throws CloneNotSupportedException {
		return (T) this.clone();
	}
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
