/**
 * Project java-code-tutorials-tools
 * File: BaseDTO.java
 * CreateTime: 2015年11月8日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.test.reflect;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * TypeName: BaseDTO
 * <P>TODO
 * 
 * <P>CreateTime: 2015年11月8日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public abstract class BaseDTO implements Serializable {
	private static final long serialVersionUID = -8677566668870788317L;
	private String subClassPath;
	private Type subClassType;
	
	/**
	 * @param subClassPath
	 * @param subClassType
	 */
	protected BaseDTO(String subClassPath, Type subClassType) {
		super();
		this.subClassPath = subClassPath;
		this.subClassType = subClassType;
	}
	/**
	 * 
	 */
	protected BaseDTO() {
		super();
	}
	/**
	 * @return the subClassPath
	 */
	public String getSubClassPath() {
		return subClassPath;
	}
	/**
	 * @param subClassPath the subClassPath to set
	 */
	protected void setSubClassPath(String subClassPath) {
		this.subClassPath = subClassPath;
	}
	/**
	 * @return the subClassType
	 */
	public Type getSubClassType() {
		return subClassType;
	}
	/**
	 * @param subClassType the subClassType to set
	 */
	protected void setSubClassType(Type subClassType) {
		this.subClassType = subClassType;
	}
	
}
