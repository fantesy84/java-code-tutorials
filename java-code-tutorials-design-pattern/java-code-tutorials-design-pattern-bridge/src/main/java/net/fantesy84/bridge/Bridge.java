/**
 * 项目名: java-code-tutorials-design-pattern-bridge
 * 包名:  net.fantesy84.source
 * 文件名: Bridge.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.bridge;

import net.fantesy84.connection.Connection;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public abstract class Bridge {
	private Connection source;
	
	public void createConnection(){
		source.sourceMethod();
	}

	/**
	 * @return the source
	 */
	public Connection getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Connection source) {
		this.source = source;
	}
	
}
