/**
 * 项目名: java-code-tutorials-design-pattern-bridge
 * 包名:  net.fantesy84.source
 * 文件名: DriverMangerMysqlImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.jdbc.driver;

import net.fantesy84.bridge.Bridge;
import net.fantesy84.connection.MysqlConnection;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class MysqlDriver extends Bridge {

	/**
	 * 
	 */
	public MysqlDriver() {
		super();
		super.setSource(new MysqlConnection());
	}

}
