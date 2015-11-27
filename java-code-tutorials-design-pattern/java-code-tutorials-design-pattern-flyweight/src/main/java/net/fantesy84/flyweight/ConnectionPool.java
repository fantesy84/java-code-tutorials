/**
 * 项目名: java-code-tutorials-design-pattern-flyweight
 * 包名:  net.fantesy84.flyweight
 * 文件名: ConnectionPool.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.flyweight;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.util.Vector;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class ConnectionPool {
	private Vector<Connection> pool;
	
	private DriverPropertyInfo info;
	
	private int initSize = 5;
	private int maxSize = 10;
	
	private ConnectionPool(){
		pool = new Vector<Connection>(initSize);
	}
	
	private static class ConnectionPoolFactory {
		public static ConnectionPool newInstance = new ConnectionPool();
	}
	
	public static ConnectionPool getInstance(){
		return ConnectionPoolFactory.newInstance;
	}
	
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			return pool.remove(0);
		} else {
			return null;
		}
	}
	
	public synchronized void release(Connection conn) {
		pool.add(conn);
	}

	/**
	 * @return the info
	 */
	public DriverPropertyInfo getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(DriverPropertyInfo info) {
		this.info = info;
	}

	/**
	 * @return the initSize
	 */
	public int getInitSize() {
		return initSize;
	}

	/**
	 * @param initSize the initSize to set
	 */
	public void setInitSize(int initSize) {
		this.initSize = initSize;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
}
