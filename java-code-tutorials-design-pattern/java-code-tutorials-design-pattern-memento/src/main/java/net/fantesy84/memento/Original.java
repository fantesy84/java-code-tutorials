/**
 * 项目名: java-code-tutorials-design-pattern-memento
 * 包名:  net.fantesy84.memento
 * 文件名: Original.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.memento;

import java.io.Serializable;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class Original implements Serializable {
	private static final long serialVersionUID = -6452776918398068409L;
	private String str;
	
	/**
	 * @param str
	 */
	public Original(String str) {
		super();
		this.str = str;
	}
	/**
	 * 
	 */
	public Original() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}
	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = str;
	}
	
	public Memento createMemento(){
		return new Memento(str);
	}
	
	public void restoreMemento(Memento m) {
		this.str = m.getArg0();
	}
}
