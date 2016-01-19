/**
 * 项目名: java-code-tutorials-tools
 * 包名:  net.fantesy84.test.util.async
 * 文件名: Entity.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月19日
 */
package net.fantesy84.test.util.async;

/**
 * @author Andronicus
 * @since 2016年1月19日
 */
public class Entity {
	private String str;
	private boolean isDone;
	
	/**
	 * @param str
	 * @param isDone
	 */
	public Entity(String str) {
		super();
		this.str = str;
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
	/**
	 * @return the isDone
	 */
	public boolean isDone() {
		return isDone;
	}
	/**
	 * @param isDone the isDone to set
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
}
