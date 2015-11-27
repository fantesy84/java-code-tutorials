/**
 * 项目名: java-code-tutorials-design-pattern-bridge
 * 包名:  net.fantesy84.test.bridge
 * 文件名: DriverManagerTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月27日
 */
package net.fantesy84.test.bridge;

import org.junit.Test;

import net.fantesy84.bridge.DriverManager;

/**
 * @author Andronicus
 * @since 2015年11月27日
 */
public class DriverManagerTest {
	@Test
	public void test1(){
		DriverManager.loadDriver("net.fantesy84.jdbc.driver.MysqlDriver");
		DriverManager.getConnection();
	}
}
