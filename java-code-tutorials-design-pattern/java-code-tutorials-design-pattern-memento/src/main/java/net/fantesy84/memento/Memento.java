/**
 * 项目名: java-code-tutorials-design-pattern-memento
 * 包名:  net.fantesy84.memento
 * 文件名: Memento.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.memento;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class Memento implements Serializable {
	private static final long serialVersionUID = 5874316348482522540L;
	private String arg0;
	
	/**
	 * @param arg0
	 */
	public Memento(String arg0) {
		super();
		this.arg0 = arg0;
	}
	/**
	 * 
	 */
	public Memento() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the arg0
	 */
	public String getArg0() {
		return arg0;
	}
	/**
	 * @param arg0 the arg0 to set
	 */
	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}
	
}
